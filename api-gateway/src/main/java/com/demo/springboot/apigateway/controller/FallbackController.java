package com.demo.springboot.apigateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 熔断回调controller
 * @date 2021/1/22 17:03
 * @see
 */
@RestController
public class FallbackController {

    @GetMapping("/fallback/api")
    public Object fallbackAPI() {
        // todo ...熔断逻辑
        return "500 server error occuers";
    }

    @GetMapping("/fallback/main")
    public Object fallbackMain() {
        // todo ...熔断逻辑
        return "500 server error occuers";
    }
}
