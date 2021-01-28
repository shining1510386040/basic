package com.demo.springboot.consulesweb.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 非响应式编程mongo配置：MongoAutoConfiguration
 * @date 2021/1/26 14:57
 * @see
 */
@Configuration
@Component
@PropertySource("classpath:application-test.yml")
public class MongoConfig {

    @Value("${spring.data.mongodb.host}")
    private String host;
    @Value("${spring.data.mongodb.username}")
    private String username;
    @Value("${spring.data.mongodb.password}")
    private String password;
    @Value("${spring.data.mongodb.database}")
    private String database;

    @Bean
    @Primary
    public MongoClient mongoClient() {
        String connStrUrl = "mongodb://" + host;
        ConnectionString connStr = new ConnectionString(connStrUrl);
        // 需要密码验证的使用：
//        return MongoClients.create(mongoClientSettings(connStr));
//        // 不需要密码验证的：
        return MongoClients.create(connStr);
    }

    private MongoClientSettings mongoClientSettings(ConnectionString connectionString) {
        // 需要密码验证：使用此方法
        MongoCredential credential = MongoCredential.createCredential(username, database, password.toCharArray());
        return MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .credential(credential)
                .build();
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 操作mongo的template模板
     * @date 2021/1/28 17:14
     */
    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), database);
    }

}
