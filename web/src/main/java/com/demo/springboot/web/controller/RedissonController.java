package com.demo.springboot.web.controller;

import com.demo.springboot.web.vo.ServiceResult;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 测试Redisson功能：
 * @date 2021/1/13 16:42
 * @see
 */
@RestController
@RequestMapping("/redisson")
public class RedissonController {

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 测试Redisson实现的分布式锁
     * @date 2021/1/13 16:46
     */
    @GetMapping("/lock")
    public ServiceResult testLock() {
        RLock lock = redissonClient.getLock("mylock");
        Object product = redisTemplate.opsForValue().get("product");
        try {
            if (product == null) {
                boolean b = lock.tryLock(10, TimeUnit.SECONDS);
                if (b) {
                    // 从db中查询
                    Object ret = new Object();
                    redisTemplate.opsForValue().set("product", ret, 20, TimeUnit.MINUTES);
                }
            } else {
                // 从缓存中取
                // ,,,,
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return new ServiceResult();
    }
}
