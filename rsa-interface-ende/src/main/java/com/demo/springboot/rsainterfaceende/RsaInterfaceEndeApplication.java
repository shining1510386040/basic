package com.demo.springboot.rsainterfaceende;

import cn.shuibo.annotation.EnableSecurity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableSecurity
public class RsaInterfaceEndeApplication {

    public static void main(String[] args) {
        SpringApplication.run(RsaInterfaceEndeApplication.class, args);
    }

}
