package com.demo.springboot.web.controller;

import com.demo.springboot.web.entity.Userinfo;
import com.demo.springboot.web.mapper.UserInfoMapper;
import com.sun.org.apache.regexp.internal.RE;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 用户controller
 * @date 2020/12/18 15:14
 * @see
 */
@RestController
@RequestMapping("/user")
public class UserInfoController {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @GetMapping("/getOne/{id}")
    public Userinfo getOne(@PathVariable long id) {

        Userinfo one = userInfoMapper.getOne(id);
        return one;
    }

    @PostMapping("/save")
    public int saveUser(Userinfo data) {
        // 参数自动绑定 对象属性
        int i = userInfoMapper.saveUser(data);
        return i;
    }

    @PostMapping("/update")
    public int updateUser(Userinfo data) {
        // 参数自动绑定 对象属性
        int i = userInfoMapper.updateUser(data);
        return i;
    }

    @PostMapping("/delete")
    public int deleteUser(@RequestParam long id) {
        // 参数自动绑定 对象属性
        int i = userInfoMapper.deleteUser(id);
        return i;
    }

    @PostMapping("/getAll")
    public List<Userinfo> getAll(Userinfo search, @RequestParam int pageNo, @RequestParam int pageSize) {
        List<Userinfo> all = userInfoMapper.getAll(search, pageNo, pageSize);
        return all;
    }

}
