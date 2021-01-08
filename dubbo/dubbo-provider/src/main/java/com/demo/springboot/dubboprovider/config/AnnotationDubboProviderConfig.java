package com.demo.springboot.dubboprovider.config;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 注解方式配置;
 * DubboAutoConfiguration
 * @date 2021/1/8 16:24
 * @see
 */
@Configuration
@EnableDubbo(scanBasePackages = "com.demo.springboot.dubboprovider.service")
@PropertySource("classpath:dubbo-provider.properties")
public class AnnotationDubboProviderConfig {
}
