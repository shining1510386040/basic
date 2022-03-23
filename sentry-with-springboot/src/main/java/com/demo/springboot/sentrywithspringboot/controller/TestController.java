package com.demo.springboot.sentrywithspringboot.controller;

import io.sentry.Sentry;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class TestController {

    @GetMapping("/index")
    public String index() {

//        int sum = 1 / 0;
        try {
            throw new Exception("This is a test.");
        } catch (Exception e) {
            Sentry.captureException(e);

        }
        return "手动抛出异常测试：Test";
    }

    @GetMapping("/testLog")
    public String testLog() {
        log.info("test接口");//最低拦截级别为warn，所以info不会输出发送到日志中心
        log.error("error"); //会显示在日志中心，并邮件通知相关联系人

        return "logback收集异常测试";
    }
}
