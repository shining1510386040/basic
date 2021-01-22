package com.demo.springboot.apigatewaysubmainservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @param
 * @author Wenyi Cao
 * @version 1.0
 * @description 启动类
 * @date 2021/1/22 15:38
 * @return
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewaySubMainServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewaySubMainServiceApplication.class, args);
    }

}
