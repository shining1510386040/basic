package com.demo.springboot.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 自定义endpoint 配置
 * @date 2021/2/8 10:54
 * @see
 */
@Configuration
public class EndPointConfig {

    @Bean
    public CustomEndPoint customEndPoint() {
        return new CustomEndPoint();
    }
}
