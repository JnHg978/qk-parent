package com.qk.management.controller;

import com.qk.common.Result;
import com.qk.util.AliYunOSSOperators;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Slf4j
@RestController
public class UploadController {

    @Autowired
    private AliYunOSSOperators aliYunOSSOperators;

    /**
     * 文件上传
     */
    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws Exception {
        log.info("文件上传开始：{}", image.getOriginalFilename());
        // 获取原始文件后缀名
        String originalFilename = image.getOriginalFilename();
        String suffixName = originalFilename.substring(originalFilename.lastIndexOf("."));
        // 生成文件夹名Avatar
        String folderName = "Avatar/";
        // 生成新的文件名 + ;
        String objectName = new StringBuilder(folderName).append(UUID.randomUUID()).append(suffixName).toString();
        String url = aliYunOSSOperators.upload(image.getBytes(), objectName);
        return Result.success(url);
    }

}