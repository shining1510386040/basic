package com.demo.springboot.dubboproviderannotation.config;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 注解方式 dubbo提供者配置
 * @date 2021/7/1 17:18
 * @see
 */
@Configuration
@EnableDubbo(scanBasePackages = "com.demo.springboot.dubboproviderannotation.service")
@PropertySource("classpath:dubbo-provider.properties")
public class AnnotationProviderConfig {
}
