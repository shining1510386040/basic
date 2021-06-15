package com.demo.springboot.springbootrediscluster.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 测试controller
 * @date 2021/6/15 11:24
 * @see
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("/add")
    public void test() {
        redisTemplate.opsForValue().set("name", "admin");
        String name = redisTemplate.opsForValue().get("name");
        System.out.println(name); //输出admin
    }

}
