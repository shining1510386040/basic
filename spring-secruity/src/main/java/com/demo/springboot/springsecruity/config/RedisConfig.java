package com.demo.springboot.springsecruity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description redis 配置
 * @date 2021/2/1 20:20
 * @see
 */
@Configuration
public class RedisConfig {

    @Autowired
    private RedisStandaloneProperties redisStandaloneProperties;

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description redis连接工厂
     * @date 2021/1/28 18:17
     */
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        // jedis pool 池
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(redisStandaloneProperties.getPoolMaxActive());
        poolConfig.setMaxIdle(redisStandaloneProperties.getPoolMaxIdle());
        poolConfig.setMinIdle(redisStandaloneProperties.getPoolMinIdle());
        poolConfig.setMaxWaitMillis(redisStandaloneProperties.getPoolMaxWait());
        // jedis client 配置
        JedisClientConfiguration clientConfig = JedisClientConfiguration.builder()
                .usePooling()
                .poolConfig(poolConfig)
                .and()
                .readTimeout(Duration.ofMillis(redisStandaloneProperties.getRedisTimeout()))
                .build();
        // 单机模式
        RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration();
        redisConfig.setHostName(redisStandaloneProperties.getHost());
        redisConfig.setPort(redisStandaloneProperties.getPort());
        // 库号：默认为0号库
        redisConfig.setDatabase(redisStandaloneProperties.getDatabase());
        if (!StringUtils.isEmpty(redisStandaloneProperties.getPassword())) {
//            redisConfig.setPassword(redisStandaloneProperties.getPassword());
            RedisPassword redisPassword = RedisPassword.of(redisStandaloneProperties.getPassword());
            redisConfig.setPassword(redisPassword);
        }
        return new JedisConnectionFactory(redisConfig, clientConfig);
    }
}
