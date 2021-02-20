package com.demo.springboot.dbconnector;

import com.demo.springboot.dbconnector.config.DataSourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @param
 * @author Wenyi Cao
 * @version 1.0
 * @description
 * @EnableTransactionManagement // 开启注解事务管理，等同于xml配置文件中的 <tx:annotation-driven />
 * @date 2021/2/20 9:49
 * @return
 */
@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass = true)
@Import({DataSourceConfig.class})
public class DbconnectorApplication {

    public static void main(String[] args) {
        SpringApplication.run(DbconnectorApplication.class, args);
    }

}
