package com.demo.springboot.springsecruity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 测试controller
 * @date 2021/2/1 19:45
 * @see
 */
@RestController
public class TestController {


    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description admin 角色才能访问此接口
     * @date 2021/2/1 19:46
     */
    @GetMapping("/admin/hello")
    public String admin() {

        return "hello admin";
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description user 角色才能访问此接口
     * @date 2021/2/1 19:46
     */
    @GetMapping("/user/hello")
    public String user() {
        return "hello user";
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 登录以后才能访问此接口
     * @date 2021/2/1 19:47
     */
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
