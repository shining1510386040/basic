package com.demo.springboot.sentinel.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.demo.springboot.sentinel.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 测试controller
 * @date 2021/2/2 16:04
 * @see
 */
@RestController
@RequestMapping("/sentinel")
public class HelloController {

    @Autowired
    private HelloService helloService;

    @GetMapping("/hello")
    @SentinelResource(value = "hello")
    public String sayHello() {

        return "hello sentinel";
    }
    // ...
}
