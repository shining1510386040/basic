package com.demo.springboot.dynamicdatasource.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.springboot.dynamicdatasource.entity.User;
import com.demo.springboot.dynamicdatasource.mapper.UserMapper;
import com.demo.springboot.dynamicdatasource.vo.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 测试controller
 * @date 2021/6/8 10:55
 * @see
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/list")
    public ServiceResult listUser() {
        // 条件封装器
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", 3);
        queryWrapper.like("name", "%o%");
        List<User> userList = userMapper.selectList(queryWrapper);
        ServiceResult ret = new ServiceResult("200", "查询成功");
        ret.setData(userList);
        return ret;
    }
}
