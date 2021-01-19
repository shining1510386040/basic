package com.demo.springboot.web.config;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 阿里云oss对象存储服务
 * @date 2021/1/18 10:29
 * @see
 */
@Data
@Configuration
@PropertySource("classpath:application-test.yml")
public class AliyunOssConfig {
    /**
     * 地域节点
     */
    @Value("${aliyun.oss.endpoint}")
    private String endpoint;
    /**
     * ram访问授权 key
     */
    @Value("${aliyun.accessKeyId}")
    private String accessKeyId;
    /**
     * ram访问授权 秘钥
     */
    @Value("${aliyun.accessKeySecret}")
    private String accessKeySecret;
    /**
     * oss存储要访问的bucket
     */
    @Value("${aliyun.oss.bucketName}")
    private String bucketName;
    /**
     * url前缀
     */
    @Value("${aliyun.oss.urlPrefix}")
    private String urlPrefix;

    @Bean
    public OSSClient ossClient() {
        ClientConfiguration configuration = new ClientConfiguration();
        DefaultCredentialProvider defaultCredentialProvider = new DefaultCredentialProvider(accessKeyId, accessKeySecret);
        OSSClient ret = new OSSClient(endpoint, defaultCredentialProvider, configuration);
        return ret;
    }
}
