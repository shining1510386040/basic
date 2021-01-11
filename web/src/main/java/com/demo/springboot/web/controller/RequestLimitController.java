package com.demo.springboot.web.controller;

import com.demo.springboot.web.annotation.RequestLimit;
import com.demo.springboot.web.vo.ServiceResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 测试接口防刷controller
 * @date 2021/1/11 10:50
 * @see
 */
@RestController
@RequestMapping("/request")
public class RequestLimitController {

    @GetMapping("/limit")
    @RequestLimit(second = 4, maxCount = 3)
    public ServiceResult get() {
        // ....do some work..
        return new ServiceResult("200", "请求成功");
    }
}
