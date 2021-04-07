package com.demo.springboot.zipkinservice.controller;

import com.demo.springboot.zipkinservice.vo.ServiceResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 测试和vue 整合接口
 * @date 2021/4/7 10:33
 * @see
 */
@RestController
public class IndexController {

    @GetMapping("/index/login")
    public ServiceResult login(@RequestParam String username, @RequestParam String password) {
        // 模拟登陆
        if ("admin".equalsIgnoreCase(username) && "admin".equalsIgnoreCase(password)) {
            ServiceResult ret = new ServiceResult("200", "登陆成功");
            ret.setData("admin");
            return ret;
        } else {
            ServiceResult ret = new ServiceResult("500", "登陆失败");
            return ret;
        }
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 获取用户列表
     * @date 2021/4/7 10:57
     */
    @PostMapping("/user/list")
    public ServiceResult listUser(@RequestParam(required = false) String username) {
        // 模拟获取用户列表
        Map<String, String> user1 = new HashMap<>(8);
        user1.put("birth", "1992-04-09");
        user1.put("username", "张三");
        user1.put("address", "北京市昌平区朱辛庄");
        Map<String, String> user2 = new HashMap<>(8);
        user2.put("birth", "1992-05-19");
        user2.put("username", "李四");
        user2.put("address", "北京市昌平区朱辛庄");
        Map<String, String> user3 = new HashMap<>(8);
        user3.put("birth", "1992-06-09");
        user3.put("username", "王五");
        user3.put("address", "北京市昌平朱辛庄");
        List<Map<String, String>> data = Arrays.asList(user1, user2, user3);
        ServiceResult ret = new ServiceResult("200", "查询用户成功");
        ret.setData(data);
        return ret;
    }

}
