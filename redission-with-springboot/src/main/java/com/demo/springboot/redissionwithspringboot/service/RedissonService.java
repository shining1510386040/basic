package com.demo.springboot.redissionwithspringboot.service;

import com.demo.springboot.redissionwithspringboot.common.ServiceResult;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RQueue;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description Redisson服务
 * @date 2021/12/6 10:52
 * @see
 */
@Service
public class RedissonService {

    @Autowired
    private RedissonClient redissonClient;

    public ServiceResult test() {
        // 队列
        RQueue queue = redissonClient.getQueue("myqueue");
        queue.peek();
        queue.offer("aaa");
        Object poll = queue.poll();
        queue.isEmpty();
        return null;
    }


    public void testBoolmfilter() {
        RBloomFilter bloomFilter = redissonClient.getBloomFilter("myfilter");
        // 初始化布隆过滤器，大小，容错率
        bloomFilter.tryInit(1000, 0.03);
        for (int i = 0; i < 10000; i++) {

            bloomFilter.add("data" + i);
        }

        boolean data3 = bloomFilter.contains("data3");

    }
}
