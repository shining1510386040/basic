package com.demo.springboot.springsecruity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 资源服务器配置
 * @date 2021/2/1 19:38
 * @see
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        // 配置资源id，这里的资源id和授权服务器中的资源id一致
        resources.resourceId("rid")
                // 设置这些资源仅基于令牌认证
                .stateless(true);
    }

    /**
     * 配置 URL 访问权限
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("admin")
                .antMatchers("/user/**").hasRole("user")
                .anyRequest().authenticated();
    }
}
