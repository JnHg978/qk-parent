package com.qk.management.config;

import com.qk.utils.AliyunOSSOperator;
import com.qk.utils.AliyunOSSProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: hjh
 * @Date: 2025/10/06 21:56
 * @Description:
 */
@Configuration
@EnableConfigurationProperties(AliyunOSSProperties.class)
public class AliyunOSSConfig {

    @Bean
    @ConditionalOnMissingBean
    public AliyunOSSOperator aliyunOSSOperator(AliyunOSSProperties aliyunOSSProperties) {
        return new AliyunOSSOperator(aliyunOSSProperties.getEndpoint(), aliyunOSSProperties.getBucketName(), aliyunOSSProperties.getRegion());
    }
}
