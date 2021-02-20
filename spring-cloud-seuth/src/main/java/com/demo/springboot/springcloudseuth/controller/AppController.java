package com.demo.springboot.springcloudseuth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 应用服务controller，生成数据给zipkin
 * @date 2021/2/20 10:15
 * @see
 */
@RestController
@RequestMapping("/app")
public class AppController {

    @GetMapping("/test")
    public String hello() {
        return "我是应用服务";
    }
}
