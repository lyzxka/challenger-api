package io.renren.app.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * @Author: zhanglei
 * @Date: 2018/8/27 10:20
 * @Description:
 */
public interface FileUploadService {

    public String uploadFile(MultipartFile file) throws IOException, NoSuchAlgorithmException;

}
