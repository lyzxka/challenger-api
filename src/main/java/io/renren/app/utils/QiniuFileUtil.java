package io.renren.app.utils;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.FetchRet;
import com.qiniu.util.Auth;
import io.renren.app.entity.Rescource;
import io.renren.app.service.RescourceService;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.UUID;

@Component
public class QiniuFileUtil {


    private static final Logger LOGGER = LoggerFactory.getLogger(QiniuFileUtil.class);

    @Autowired
    RescourceService rescourceService;


    /***
     * 普通上传图片
     * @param file
     * @return
     * @throws QiniuException
     * @throws IOException
     */
    public  String upload(MultipartFile file, Map<String, Object> map) throws IOException, NoSuchAlgorithmException {
        Zone z = Zone.zone0();
        Configuration config = new Configuration(z);
        String fileName = "", extName = "", filePath = "";
        if (null != file && !file.isEmpty()) {
            extName = file.getOriginalFilename().substring(
                    file.getOriginalFilename().lastIndexOf("."));
            fileName = UUID.randomUUID() + extName;
            UploadManager uploadManager = new UploadManager(config);
            Auth auth = Auth.create(map.get("qiniuAccess").toString(), map.get("qiniuKey").toString());
            String token = auth.uploadToken(map.get("bucketName").toString());
            byte[] data = file.getBytes();
            QETag tag = new QETag();
            String hash = tag.calcETag(file);
            Rescource rescource = rescourceService.selectRescourceByHash(hash);
            if (rescource != null) {
                return rescource.getWebUrl();
            }
            Response r = uploadManager.put(data, fileName, token);
            if (r.isOK()) {
                filePath = map.get("path") + fileName;
                rescource = new Rescource();
                rescource.setFileName(fileName);
                rescource.setFileSize(new java.text.DecimalFormat("#.##").format(file.getSize() / 1024) + "kb");
                rescource.setHash(hash);
                rescource.setFileType(StringUtils.isBlank(extName) ? "unknown" : extName);
                rescource.setWebUrl(filePath);
                rescource.setSource("qiniu");
                rescourceService.save(rescource);
            }
        }
        return filePath;
    }

    /***
     * 普通上传图片
     * @return
     * @throws QiniuException
     * @throws IOException
     */
    public  String upload(InputStream inputStream, Map<String, Object> map) throws IOException, NoSuchAlgorithmException {
        Zone z = Zone.zone0();
        Configuration config = new Configuration(z);
        String fileName = "",filePath="";
        fileName = UUID.randomUUID() + ".jpg";
        UploadManager uploadManager = new UploadManager(config);
        Auth auth = Auth.create(map.get("qiniuAccess").toString(), map.get("qiniuKey").toString());
        String token = auth.uploadToken(map.get("bucketName").toString());
        Response r = uploadManager.put(inputStream, fileName, token,null,null);
        if (r.isOK()) {
            filePath = map.get("path") + fileName;
        }
        return filePath;
    }

    /***
     * 删除已经上传的图片
     * @param imgPath
     */
    public void deleteQiniuP(String imgPath, Map<String, Object> map) {
        Zone z = Zone.zone0();
        Configuration config = new Configuration(z);
        Auth auth = Auth.create(map.get("qiniuAccess").toString(), map.get("qiniuKey").toString());
        BucketManager bucketManager = new BucketManager(auth, config);
        imgPath = imgPath.replace(map.get("path").toString(), "");
        try {
            bucketManager.delete(map.get("bucketName").toString(), imgPath);
        } catch (QiniuException e) {
            e.printStackTrace();
        }
    }

    /***
     * 上传网络图片
     * @param src
     * @return
     */
    public String uploadImageSrc(String src, Map<String, Object> map) {
        Zone z = Zone.zone0();
        Configuration config = new Configuration(z);
        Auth auth = Auth.create(map.get("qiniuAccess").toString(), map.get("qiniuKey").toString());
        BucketManager bucketManager = new BucketManager(auth, config);
        String fileName = UUID.randomUUID().toString(), filePath = "";
        try {
            FetchRet fetchRet = bucketManager.fetch(src, map.get("bucketName").toString());
            filePath = map.get("path").toString() + fetchRet.key;
            Rescource rescource = new Rescource();
            rescource.setFileName(fetchRet.key);
            rescource.setFileSize(new java.text.DecimalFormat("#.##").format(fetchRet.fsize / 1024) + "kb");
            rescource.setHash(fetchRet.hash);
            rescource.setFileType(fetchRet.mimeType);
            rescource.setWebUrl(filePath);
            rescource.setSource("qiniu");
            rescourceService.save(rescource);
        } catch (QiniuException e) {
            filePath = src;
            e.printStackTrace();
        }
        return filePath;
    }


    /**
     * 上传base64位的图片
     *
     * @param base64
     * @return
     */
    public String uploadBase64(String base64, String name, Map<String, Object> map) {
        Zone z = Zone.zone0();
        Configuration config = new Configuration(z);
        UploadManager uploadManager = new UploadManager(config);
        Auth auth = Auth.create(map.get("qiniuAccess").toString(), map.get("qiniuKey").toString());
        String token = auth.uploadToken(map.get("bucketName").toString()), filePath;

        byte[] data = Base64.decodeBase64(base64);
        try {
            uploadManager.put(data, name, token);
        } catch (IOException e) {
            e.printStackTrace();
        }
        filePath = map.get("path").toString() + name;
        return filePath;
    }

}
