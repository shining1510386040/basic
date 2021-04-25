package com.demo.springboot.eurekaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @param
 * @author Wenyi Cao
 * @version 1.0
 * @description EnableEurekaServer: 作为注册中心-服务端
 * @date 2021/4/25 11:00
 * @return
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServiceApplication.class, args);
    }

}
