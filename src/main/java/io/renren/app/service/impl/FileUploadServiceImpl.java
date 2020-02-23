package io.renren.app.service.impl;

import io.renren.app.service.FileUploadService;
import io.renren.app.utils.QiniuFileUtil;
import io.renren.config.QiniuConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * @Author: zhanglei
 * @Date: 2018/8/27 10:43
 * @Description:
 */
@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Autowired
    QiniuFileUtil qiniuFileUtil;
    @Autowired
    QiniuConfig qiniuConfig;


    @Override
    public String uploadFile(MultipartFile file) throws IOException, NoSuchAlgorithmException {
        return qiniuFileUtil.upload(file,qiniuConfig.getQiniu());
    }
}
