package com.woniuxy.mall.service.impl;

import com.woniuxy.mall.config.MinioConfig;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.http.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class MinioService {
    @Autowired
    public MinioClient minioClient;

    @Autowired
    private MinioConfig minioConfig;

    public String uploadToMinIO(MultipartFile file) {
        PutObjectArgs objectArgs=null;
        String originalFileName= file.getOriginalFilename();
        String newFileName= UUID.randomUUID().toString().replaceAll("-","");
        String suffix=originalFileName.substring(originalFileName.lastIndexOf("."));
        try {
            objectArgs = PutObjectArgs.builder().bucket(minioConfig.getBucketName()).object("aaa.jpg")
                    .stream(file.getInputStream(), file.getSize(), -1).contentType(file.getContentType()).build();
            //文件名称相同会覆盖
            minioClient.putObject(objectArgs);
            GetPresignedObjectUrlArgs build = new GetPresignedObjectUrlArgs().builder().bucket("goods").object("aaa.jpg").method(Method.GET).build();
            String url = minioConfig.getEndpoint()+"/"+minioConfig.getBucketName()+"/"+newFileName+suffix;

            return url;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
