package com.demo.springboot.web.controller;

import com.demo.springboot.web.mapper.UserInfoMapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description restcontroller
 * @date 2020/12/18 15:14
 * @see
 */
@RestController
@RequestMapping("/rest")
public class HelloRestController {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @GetMapping("/getOne/{id}")
    public String getOne(@PathVariable long id){

        return "hhaha";
    }
}
