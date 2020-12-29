package com.demo.springboot.web.controller;

import com.demo.springboot.web.entity.Userinfo;
import com.demo.springboot.web.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 登录
 * @date 2020/12/18 19:52
 * @see
 */
@Controller
//@RequestMapping("/view")
public class LoginController {

    @Autowired
    private UserInfoMapper userInfoMapper;

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 转到登录页面
     * @date 2020/12/18 20:21
     */
//    @GetMapping(value = {"/toLogin", "/", "index.html"})
    public String toLogin() {

        return "view/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Map<String, Object> map, HttpSession session) {

        if (doLogin(username, password)) {
            // 登录成功，设置session，重定向到主页
            session.setAttribute("loginUser", username);
            return "redirect:/home.html";

        } else {
            // 登录失败，返回提示信息，转发到登录页
            map.put("msg", "登录失败");
            return "view/login";
        }
    }

    // 登录逻辑
    private boolean doLogin(String username, String password) {


        Userinfo ret = userInfoMapper.getUserWithNameAndPass(username, password);

        if (ret != null) {
            return true;
        } else {
            return false;
        }
    }
}
