package com.qk.management.config;


import com.qk.property.AliyunOSSProperties;
import com.qk.util.AliYunOSSOperators;
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
    public AliYunOSSOperators aliyunOSSOperator() {
        return new AliYunOSSOperators();
    }
}
