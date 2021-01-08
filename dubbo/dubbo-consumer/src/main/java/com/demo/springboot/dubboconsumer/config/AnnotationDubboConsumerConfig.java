package com.demo.springboot.dubboconsumer.config;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description dubbo服务调用方，注解配置
 * @date 2021/1/8 16:34
 * @see
 */
@Configuration
@EnableDubbo(scanBasePackages = "com.demo.springboot.dubboconsumer.controller")
@PropertySource("classpath:dubbo-consumer.properties")
public class AnnotationDubboConsumerConfig {

}
