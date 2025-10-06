package com.qk.utils;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.common.comm.SignVersion;
import com.aliyun.oss.model.PutObjectRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;

@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class AliyunOSSOperator {

    /*
    @Value("${aliyun.oss.endpoint}")
    private String endpoint;

    @Value("${aliyun.oss.bucketName}")
    private String bucketName;

    @Value("${aliyun.oss.region}")
    private String region;
    */
    String endpoint;
    String bucketName;
    String region;

    public String upload(byte[] content, String objectName) throws Exception {

        // 创建OSSClient实例
        ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
        clientBuilderConfiguration.setSignatureVersion(SignVersion.V4);
        OSS ossClient = null;
        try {
            ossClient = OSSClientBuilder.create()
                    .endpoint(endpoint)
                    .credentialsProvider(new EnvironmentVariableCredentialsProvider())
                    .clientConfiguration(clientBuilderConfiguration)
                    .region(region)
                    .build();
            // 创建PutObjectRequest对象
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, new ByteArrayInputStream(content));
            // 上传文件
            ossClient.putObject(putObjectRequest);
            // 返回文件访问URL
            return "https://" + bucketName + "." + endpoint.substring(8) + "/" + objectName;
        } catch (Exception e) {
            log.error("Caught an OSSException: " + e.getMessage());
            throw e;
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }
}