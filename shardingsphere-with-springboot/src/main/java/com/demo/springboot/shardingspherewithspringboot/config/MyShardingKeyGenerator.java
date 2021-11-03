package com.demo.springboot.shardingspherewithspringboot.config;

import org.apache.shardingsphere.spi.keygen.ShardingKeyGenerator;
import org.springframework.stereotype.Component;

import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 自定义分布式 主键生成策略
 * @date 2021/11/3 16:27
 * @see
 */
@Component
public class MyShardingKeyGenerator implements ShardingKeyGenerator {


    private final AtomicInteger count = new AtomicInteger();

    /**
     * 自定义的生成方案类型
     */
    @Override
    public String getType() {
        return "MyType";
    }

    /**
     * 核心方法-生成主键ID
     */
    @Override
    public Comparable<?> generateKey() {
        return count.incrementAndGet();
    }

    @Override
    public Properties getProperties() {

        return null;
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
