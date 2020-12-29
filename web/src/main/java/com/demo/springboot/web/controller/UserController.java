package com.demo.springboot.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 用户controller
 * @date 2020/12/28 18:12
 * @see
 */
@Controller
@RequestMapping
public class UserController {


    @GetMapping(value = {"/", "/index", "index.html"})
    public String index() {
        return "index";
    }


    @GetMapping("/user/add")
    public String toAdd() {
        return "user/add";
    }

    @GetMapping("/user/update")
    public String toUpdate() {
        return "user/update";
    }

    @GetMapping("/user/toLogin")
    public String toLogin() {
        return "user/login";
    }


}
