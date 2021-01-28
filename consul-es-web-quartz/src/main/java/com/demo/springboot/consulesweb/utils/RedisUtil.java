package com.demo.springboot.consulesweb.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description redis 操作工具
 * @date 2021/1/28 18:21
 * @see
 */
@Component
public class RedisUtil {

    @Autowired
    private Jedis jedis;

    @Autowired
    private RedisTemplate redisTemplate;

    public String set() {
        // todo ....
        jedis.set("", "");
        redisTemplate.opsForValue().set("", "");
        return "";
    }
}
