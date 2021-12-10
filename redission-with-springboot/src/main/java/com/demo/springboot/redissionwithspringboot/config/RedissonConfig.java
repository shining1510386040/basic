package com.demo.springboot.redissionwithspringboot.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;
import java.util.HashSet;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description redisson配置
 * @date 2021/1/13 17:17
 * @see
 */
@Configuration
public class RedissonConfig {

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        // cluster 集群模式
        config.useClusterServers()
                .setScanInterval(2000)
                .addNodeAddress("redis://127.0.0.1:6381", "redis://127.0.0.1:6382", "redis://127.0.0.1:6383")
                .addNodeAddress("redis://127.0.0.1:6384", "redis://127.0.0.1:6385", "redis://127.0.0.1:6386");
        // 设置编码器：默认为JsonJacksonCodec
        config.setCodec(new FastjsonCodec());
        RedissonClient redisson = Redisson.create(config);
//        // single 单机模式
//        config.useSingleServer().setAddress("redis://127.0.0.1:6379").setDatabase(2);
//        // redis 主从模式
//        config.useMasterSlaveServers().setMasterAddress("").setSlaveAddresses(new HashSet<String>());
//        // redis 哨兵
//        config.useSentinelServers().addSentinelAddress("", "");
        return redisson;
    }
}
