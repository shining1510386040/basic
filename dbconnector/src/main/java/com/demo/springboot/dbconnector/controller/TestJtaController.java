package com.demo.springboot.dbconnector.controller;

import com.demo.springboot.dbconnector.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description Jta事务测试controller
 * @date 2021/2/19 19:57
 * @see
 */
@RestController
@RequestMapping("/jta")
public class TestJtaController {

    @Autowired
    private OrderServiceImpl orderService;

    @GetMapping("/test")
    public String test() {
        String order = orderService.createOrder();
        return order;
    }
}
