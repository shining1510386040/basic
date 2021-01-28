package com.demo.springboot.consulesweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.elasticsearch.repository.config.EnableReactiveElasticsearchRepositories;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.ldap.repository.config.EnableLdapRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @param
 * @author Wenyi Cao
 * @version 1.0
 * @description EnableDiscoveryClient:开启服务发现，
 * EnableMongoRepositories:开启mongo仓储
 * EnableReactiveMongoRepositories：开启mongo响应式仓储
 * EnableReactiveElasticsearchRepositories：开启es 响应式仓储
 * EnableElasticsearchRepositories：开启es仓储
 * EnableJpaRepositories：开启jpa仓储
 * EnableJdbcRepositories：开启Jdbc仓储
 * EnableScheduling：开启定时任务
 * EnableLdapRepositories：开启ldap 目录仓储
 * EnableRedisRepositories：开启redis仓储
 * @date 2021/1/26 11:00
 * @return
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableReactiveElasticsearchRepositories
@EnableMongoRepositories
@EnableJpaRepositories
@EnableJdbcRepositories
@EnableScheduling
@EnableReactiveMongoRepositories
@EnableLdapRepositories
@EnableRedisRepositories
@EnableElasticsearchRepositories
public class ConsulEsWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsulEsWebApplication.class, args);
    }

}
