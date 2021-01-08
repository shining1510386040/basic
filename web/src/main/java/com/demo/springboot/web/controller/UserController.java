package com.demo.springboot.web.controller;

import com.demo.springboot.web.entity.UserPermissionShiro;
import com.demo.springboot.web.entity.Userinfo;
import com.demo.springboot.web.mapper.UserInfoMapper;
import com.demo.springboot.web.mapper.UserPermissionMapperShiro;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private UserPermissionMapperShiro userInfoMapper;

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 到登录页面
     * @date 2021/1/8 18:10
     */
    @GetMapping(value = {"/toLogin", "/", "/index", "index.html"})
    public String index() {

        return "login";
    }


    /**
     * 到首页
     */
    @GetMapping("/toIndex")
    public String toIndex() {
        return "user/index";
    }

    /**
     * 到未授权的页面
     */
    @GetMapping("/authorized")
    public String toAuthorized() {
        return "noright";
    }

    @GetMapping("/user/add")
    public String toAdd() {
        return "user/add";
    }

    @GetMapping("/user/update")
    public String toUpdate() {
        return "user/update";
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 登录功能
     * @date 2021/1/8 18:17
     */
    @PostMapping("/user/login")
    public String login(@RequestParam String username, @RequestParam String password) {

        UserPermissionShiro ret = userInfoMapper.getUserWithNameAndPass(username, password);
        if (ret != null) {
            try {
                // 登录主体
                Subject subject = SecurityUtils.getSubject();
                UsernamePasswordToken token = new UsernamePasswordToken(username, password);
                // 没有抛出异常则，登录成功。
                subject.login(token);
            } catch (AuthenticationException e) {
                e.printStackTrace();
                System.out.println("登录出错。");
            }
            // 重定向到主页
            return "redirect:/toIndex";
        } else {
            //登录页面
            return "login";
        }
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 注销（登出）功能
     * @date 2021/1/8 18:17
     */
    @RequestMapping(value = "/user/logout", method = {RequestMethod.GET, RequestMethod.POST})
    public String logout() {

        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        // 重定向到主页
        return "redirect:/index";
    }


}
