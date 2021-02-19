package com.demo.springboot.dbconnector;

import com.demo.springboot.dbconnector.config.DataSourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass = true)
@Import({ DataSourceConfig.class })
public class DbconnectorApplication {

    public static void main(String[] args) {
        SpringApplication.run(DbconnectorApplication.class, args);
    }

}
