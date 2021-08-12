package com.demo.springboot.sentrywithspringboot.controller;

import io.sentry.Sentry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description sentry 测试controller
 * @date 2021/8/12 13:51
 * @see
 */
@RestController
@RequestMapping("/sentry/test")
public class TestController {

    @GetMapping("/index")
    public String index() {

//        int sum = 1 / 0;
        try {
            throw new Exception("This is a test.");
        } catch (Exception e) {
            Sentry.captureException(e);
        }
        return "Test";
    }
}
