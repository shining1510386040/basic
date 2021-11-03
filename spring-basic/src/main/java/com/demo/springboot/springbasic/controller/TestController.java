package com.demo.springboot.springbasic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 测试controller
 * @date 2021/8/27 16:29
 * @see
 */
@RestController
@RequestMapping("/test")
public class TestController {


    @GetMapping("/test")
    public String test() {
        return "";
    }

}
