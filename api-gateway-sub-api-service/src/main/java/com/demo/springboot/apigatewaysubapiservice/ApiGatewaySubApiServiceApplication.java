package com.demo.springboot.apigatewaysubapiservice;

import com.alibaba.nacos.spring.context.annotation.config.EnableNacosConfig;
import com.alibaba.nacos.spring.context.annotation.discovery.EnableNacosDiscovery;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @param
 * @author Wenyi Cao
 * @version 1.0
 * @description 加上@EnableDiscoveryClient注解才能将服务注册到注册中心
 * @date 2021/1/22 15:36
 * @return
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewaySubApiServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewaySubApiServiceApplication.class, args);
    }

}
