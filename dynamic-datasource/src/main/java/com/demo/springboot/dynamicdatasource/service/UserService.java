package com.demo.springboot.dynamicdatasource.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.springboot.dynamicdatasource.entity.User;
import com.demo.springboot.dynamicdatasource.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description userservice实现
 * @date 2021/6/8 11:30
 * @see
 */
@Service
public class UserService extends ServiceImpl<UserMapper, User> {

    // todo 扩展接口

    public Map getQ() {
//        super.getOne();
        return null;
    }
}
