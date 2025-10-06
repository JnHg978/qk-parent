package com.qk.management.controller;

import com.qk.common.Result;
import com.qk.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Slf4j
@RestController
public class UploadController {

    /**
     * 文件上传
     */
    /*@PostMapping("/upload")
    public Result upload(String username, Integer age, MultipartFile file) throws Exception {
        log.info("上传文件开始...{}, {}, {}", username, age, file);
        //生成一个新的不重复的文件名
        String newFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        // 保存文件
        file.transferTo(new File("D:/images/" + newFileName));
        return Result.success();
    }*/

    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    /**
     * 文件上传
     */
    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws Exception {
        log.info("文件上传开始：{}", image.getOriginalFilename());
        //获取原始文件后缀名
        String originalFilename = image.getOriginalFilename();
        String suffixName = originalFilename.substring(originalFilename.lastIndexOf("."));
        //生成新的文件名
        String objectName = UUID.randomUUID().toString() + suffixName;
        String url = aliyunOSSOperator.upload(image.getBytes(), objectName);
        return Result.success(url);
    }

}