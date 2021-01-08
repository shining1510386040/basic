package com.demo.springboot.web.shiro;

import com.demo.springboot.web.entity.UserPermissionShiro;
import com.demo.springboot.web.mapper.UserPermissionMapperShiro;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 自定义认证和授权realm
 * @date 2020/12/28 17:50
 * @see
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserPermissionMapperShiro userMapper;

    /**
     * 授权逻辑
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
        System.out.println("执行授权逻辑");
        // 简单授权
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        // 获取当前用户主体
        Subject subject = SecurityUtils.getSubject();
        // 获取用户信息
        UserPermissionShiro userInfo = (UserPermissionShiro) subject.getPrincipal();
        // 添加权限：String Object
        simpleAuthorizationInfo.addStringPermission(userInfo.getUserPermission());
//        simpleAuthorizationInfo.addObjectPermission();

        return simpleAuthorizationInfo;
    }

    /**
     * 认证逻辑
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行认证逻辑");
        // 获取当前的用户
        Subject subject = SecurityUtils.getSubject();
        // 获取登录信息
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        // 获取用户名 密码 db去取
        UserPermissionShiro byUsername = userMapper.getByUsername(userToken.getUsername());
        if (byUsername == null) {
            // 没有此用户
            return null;
        }
        Session session = subject.getSession();
        session.setAttribute("loginUser",byUsername);
        if (!userToken.getUsername().equals(byUsername.getUserName())){
            // 判断登录的用户名，密码 是否匹配数据库中的
            // 抛出异常
            return null;
        }
        // 密码认证，shiro做了？todo
        return new SimpleAuthenticationInfo(byUsername,byUsername.getPassword(),"");
    }
}
