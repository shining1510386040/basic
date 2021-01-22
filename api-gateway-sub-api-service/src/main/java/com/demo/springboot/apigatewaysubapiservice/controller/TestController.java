package com.demo.springboot.apigatewaysubapiservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 测试controller
 * @date 2021/1/22 10:58
 * @see
 */
@RestController
@RequestMapping("/api/test")
public class TestController {

    @Value("${server.port}")
    private int port;

//    @Autowired
//    private LoadBalancerClient loadBalancerClient;

    @GetMapping("/hello")
    public String hello() {
        System.out.println("这里是api-gateway下游服务：api-service，端口：" + port);
        return "这里是api-gateway下游服务：api-service，端口：" + port;
    }
}
