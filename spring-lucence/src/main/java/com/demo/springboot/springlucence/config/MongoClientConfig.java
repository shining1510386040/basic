package com.demo.springboot.springlucence.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

/**
 * 集成Spring data mongodb，当前驱动版本：mongodb-driver-core-4.0.5.jar
 * Mongod多数据源配置，可以支持多租户用户数据库
 * MongoClientSettings为新版本mongodb驱动方法，支持3.7版本以上驱动，注意驱动版本
 * 参阅：https://mongodb.github.io/mongo-java-driver/3.9/javadoc/com/mongodb/MongoClientSettings.html
 *
 * @author zhengyan
 * 2020-11-19
 */
@Component
@ConfigurationProperties(prefix = "database.mongodb.customer")
public class MongoClientConfig {

    // 读取配置文件里的值，部署后IP或者数据库名变化，不需重新打包
    @Value("${database.mongodb.customer.host}")
    private String host;
    @Value("${database.mongodb.customer.username}")
    private String username;
    @Value("${database.mongodb.customer.password}")
    private String password;
    @Value("${database.mongodb.customer.database}")
    private String userdb;

    @Override
    public String toString() {
        return "MongoClientConfig{" + "host='" + host + "'}";
    }

    @Bean
    public MongoTemplate getUserMongoTemplate() {
        return new MongoTemplate(mongoClient(), userdb);
    }

    @Bean
    public MongoClient mongoClient() {
        //private String connStrUrl="mongodb://cloudcc:ccadmin1QAZ@pub.mongodb.rds.aliyuncs.com:3717,pub.mongodb.rds.aliyuncs.com:3717/admin?replicaSet=mgset-10557091";
        String connStrUrl = "mongodb://" + host;
        ConnectionString connStr = new ConnectionString(connStrUrl);
        return MongoClients.create(mongoClientSettings(connStr));
    }

    public MongoClientSettings mongoClientSettings(ConnectionString connectionString) {

        MongoCredential credential = MongoCredential.createCredential(username, userdb, password.toCharArray());
        return MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .credential(credential)
                .build();
    }
}
