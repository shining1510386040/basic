package com.demo.springboot.consulesweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description consul 注册中心测试
 * @date 2021/1/26 14:02
 * @see
 */
@RestController
@RequestMapping("/discovery")
public class ConsulDiscoveryController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping("/getSvc")
    public Object getSvc() {
        List<String> services = discoveryClient.getServices();
        System.out.println("=================》》》服务列表：" + services);
        return services;
    }

    @GetMapping("/discovery")
    public Object discovery() {
        String ret = loadBalancerClient.choose("consul-es-web-service").getUri().toString();
        return ret;
    }


}
