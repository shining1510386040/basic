package com.demo.springboot.consulesweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @param
 * @author Wenyi Cao
 * @version 1.0
 * @description
 * @EnableDiscoveryClient:开启服务发现， 打开此注解，consul作为注册中心和配置中心的配置才生效
 * @date 2021/1/26 11:00
 * @return
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ConsulEsWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsulEsWebApplication.class, args);
    }

}
