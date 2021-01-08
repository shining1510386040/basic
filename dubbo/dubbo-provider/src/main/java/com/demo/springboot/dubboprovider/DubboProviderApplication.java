package com.demo.springboot.dubboprovider;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;

@SpringBootApplication
//@DubboComponentScan(basePackages = "com.demo.springboot.dubboprovider.service")
public class DubboProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboProviderApplication.class, args);
    }

    /**
     * spring boot 启动完成后，初始化操作；
     * 暴露服务方式1：基于xml
     */
    @PostConstruct
    public void init() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:provider.xml");

    }

}
