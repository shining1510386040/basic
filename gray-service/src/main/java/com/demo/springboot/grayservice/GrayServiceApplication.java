package com.demo.springboot.grayservice;

import cn.springcloud.gray.server.EnableGrayServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @param
 * @author Wenyi Cao
 * @version 1.0
 * @description EnableDiscoveryClient:1.作为一个服务，向注册中心 注册服务
 * 2. 拉去注册中心的服务列表，负载均衡选择一个服务实例进行调用
 * @date 2021/4/25 11:06
 * @return
 */
@SpringBootApplication
@EnableGrayServer
@EnableDiscoveryClient
public class GrayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GrayServiceApplication.class, args);
    }

}
