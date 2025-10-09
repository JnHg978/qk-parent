package com.qk.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @Author: hjh
 * @Date: 2025/09/27 20:43
 * @Description: 轻客管家项目启动类
 */
@ServletComponentScan
@SpringBootApplication
public class QkManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(QkManagementApplication.class, args);
    }
}
