package com.demo.springboot.dubboconsumer.config;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.Configuration;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description dubbo服务消费者配置
 * @date 2021/7/1 17:32
 * @see
 */
@Configuration
@EnableDubbo(scanBasePackages = "com.demo.springboot.dubboconsumer.controller")
public class AnnotationConsumerConfig {
}
