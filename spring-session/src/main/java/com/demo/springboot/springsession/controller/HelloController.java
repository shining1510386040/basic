package com.demo.springboot.springsession.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 测试controller
 * @date 2021/2/1 16:59
 * @see
 */
@RestController
@RequestMapping("/spring-session")
public class HelloController {

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 测试httpSession
     * @date 2021/2/1 18:48
     */
    @GetMapping("/hello")
    public String hello(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("accessToken", "userLoginInfo");
        System.out.println("==========>>session:" + session);
        return "hello ,spring session is running .";
    }
}
