package com.demo.springboot.web.config;

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
//@Configuration
public class RedissonConfig {

//    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        // cluster 集群模式
        config.useClusterServers()
                .setScanInterval(2000)
                .addNodeAddress("redis://10.0.29.30:6379", "redis://10.0.29.95:6379")
                .addNodeAddress("redis://10.0.29.205:6379");

        RedissonClient redisson = Redisson.create(config);
        // single 单机模式
        config.useSingleServer().setAddress("redis://127.0.0.1:6379").setDatabase(2);
        // redis 主从模式
        config.useMasterSlaveServers().setMasterAddress("").setSlaveAddresses(new HashSet<URI>());
        // redis 哨兵
        config.useSentinelServers().addSentinelAddress("","");
        return redisson;
    }
}
