package com.demo.springboot.miniocdn.config;

import io.minio.MinioClient;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 小文件存储minIO配置
 * @date 2021/8/30 14:47
 * @see
 */
@Configuration
public class MinioConfig {

    @Value("${minio.endpoint}")
    private String endpoint;

    @Value("${minio.accesskey}")
    private String accesskey;

    @Value("${minio.secretkey}")
    private String secretkey;

    @Bean
    public MinioClient minioClient() throws InvalidPortException, InvalidEndpointException {

        MinioClient minioClient = new MinioClient(endpoint, accesskey, secretkey);
        return minioClient;
    }


}

