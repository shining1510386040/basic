package com.demo.springboot.consulesweb.config;

import com.mongodb.MongoClientSettings;
import com.mongodb.reactivestreams.client.MongoClient;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.mongo.MongoClientSettingsBuilderCustomizer;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.autoconfigure.mongo.ReactiveMongoClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 响应式编程mongo配置：MongoReactiveAutoConfiguration
 * @date 2021/1/26 14:55
 * @see
 */
@Configuration
public class ReactiveMongoConfig {

//    @Bean
//    public MongoClient reactiveStreamsMongoClient(MongoProperties properties, Environment environment, ObjectProvider<MongoClientSettingsBuilderCustomizer> builderCustomizers, ObjectProvider<MongoClientSettings> settings) {
//        ReactiveMongoClientFactory factory = new ReactiveMongoClientFactory(properties, environment, (List) builderCustomizers.orderedStream().collect(Collectors.toList()));
//        return factory.createMongoClient(settings.getIfAvailable());
//    }
}
