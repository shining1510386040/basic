package com.demo.springboot.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description TODO
 * @date 2020/12/18 15:23
 * @see
 */
@Controller
@RequestMapping("/order")
public class HelloController {

    @GetMapping("/index")
    public String index(){
        return "order/orderIndex";
    }
}
