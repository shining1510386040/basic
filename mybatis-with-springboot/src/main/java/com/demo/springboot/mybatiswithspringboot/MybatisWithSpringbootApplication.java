package com.demo.springboot.mybatiswithspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class MybatisWithSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisWithSpringbootApplication.class, args);
    }

}
