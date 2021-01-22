package com.demo.springboot.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @param
 * @author Wenyi Cao
 * @version 1.0
 * @description @EnableDiscoveryClient 开启服务发现,才能将服务注册到注册中心
 * @date 2021/1/22 14:27
 * @return
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }


}
