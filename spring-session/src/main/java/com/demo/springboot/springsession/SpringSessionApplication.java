package com.demo.springboot.springsession;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @param
 * @author Wenyi Cao
 * @version 1.0
 * @description 启动类
 * EnableDiscoveryClient：两层语义：1，本服务作为客户端注册到注册中心；2.开启服务发现功能，发现注册中心的服务列表
 * @date 2021/2/1 18:51
 * @return
 */
@SpringBootApplication
@EnableRedisHttpSession
@EnableDiscoveryClient
public class SpringSessionApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSessionApplication.class, args);
    }

}
