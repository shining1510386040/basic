package com.demo.springboot.dynamicdatasource;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @param
 * @author Wenyi Cao
 * @version 1.0
 * @description mapperScan:指定扫描的mapper文件夹，mybatis会为接口生成相对应的代理
 * exclude:排除DruidDataSourceAutoConfigure配置类
 * Q：为什么要排除DruidDataSourceAutoConfigure ？
 * A：DruidDataSourceAutoConfigure会注入一个DataSourceWrapper，其会在原生的spring.datasource下找url,username,password等。而我们动态数据源的配置路径是变化的。
 * @date 2021/6/8 10:53
 * @return
 */
@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
@MapperScan("com.demo.springboot.dynamicdatasource.mapper")
public class DynamicDatasourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DynamicDatasourceApplication.class, args);
    }

}
