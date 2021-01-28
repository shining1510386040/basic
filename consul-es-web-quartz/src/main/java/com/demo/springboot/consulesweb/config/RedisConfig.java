package com.demo.springboot.consulesweb.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description redis的配置;
 * @date 2020/12/24 15:45
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
     * @description redisTemplate 模板;
     * @date 2020/12/24 16:53
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        // 1.k-v 序列化设置为fastjson
        FastJson2JsonRedisSerializer serializer = new FastJson2JsonRedisSerializer(Object.class);

        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        serializer.setObjectMapper(mapper);

        // key的序列化方式
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        // value的序列化方式
        template.setValueSerializer(serializer);
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description redis 单机版连接客户端jedis
     * @date 2021/1/27 16:59
     */
    @Bean
    public Jedis getJedis() {
        String host = redisStandaloneProperties.getHost();
        int port = redisStandaloneProperties.getPort();
        return new Jedis(host, port);
    }


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
        if (StringUtils.isNotBlank(redisStandaloneProperties.getPassword())) {
            redisConfig.setPassword(redisStandaloneProperties.getPassword());
        }
        return new JedisConnectionFactory(redisConfig, clientConfig);
    }

}
