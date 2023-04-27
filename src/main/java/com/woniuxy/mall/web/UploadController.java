package com.woniuxy.mall.web;

import com.woniuxy.mall.service.impl.MinioService;
import com.woniuxy.mall.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class UploadController {
    @Autowired
    private MinioService minioService;
    @PostMapping("/upload")
    public ResponseResult<String>uploadImage(MultipartFile file){
        String url=minioService.uploadToMinIO(file);
        return new ResponseResult<>(url);
    }
}
