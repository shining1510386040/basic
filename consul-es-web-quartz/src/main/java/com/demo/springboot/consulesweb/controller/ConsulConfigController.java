package com.demo.springboot.consulesweb.controller;

import com.demo.springboot.consulesweb.config.StudentProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 测试consul config配置中心
 * @date 2021/1/26 14:00
 * @see
 */
@RestController
@RequestMapping("/config")
public class ConsulConfigController {

    @Autowired
    private StudentProperties studentProperties;

    @GetMapping("/get")
    public Object getPro() {
        // todo ..有问题。。
        return studentProperties;
    }
}
