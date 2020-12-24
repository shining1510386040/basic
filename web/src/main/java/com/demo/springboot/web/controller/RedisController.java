package com.demo.springboot.web.controller;

import com.demo.springboot.web.entity.OrderInfo;
import com.demo.springboot.web.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 测试操作redis的controller
 * @date 2020/12/24 15:57
 * @see
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisUtils redisUtils;

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 字符串类型的操作
     * @date 2020/12/24 15:59
     */
    @GetMapping("/string")
    public String stringOps() {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setId(1234L);
        orderInfo.setOrderName("苹果手机订单");
        orderInfo.setOrderDesc("我是苹果手机订单。。");
        orderInfo.setTotalPrice(12344);
        // 1.添加
        String key = "redis:string:orderId";
        boolean set = redisUtils.set(key, orderInfo);
        //2.获取值
        Object o = redisUtils.get(key);
        System.out.println(o);
        //3.设置key过期时间TTL
        boolean expire = redisUtils.expire(key, 30);
        // 4.获取key过期时间
        long expire1 = redisUtils.getExpire(key);
        System.out.println(expire1);
        return "ok";
    }

    @GetMapping("/hash")
    public String hashOps() {
        // todo ...
        return "ok";
    }

    @GetMapping("/list")
    public String listOps() {
        // todo ....
        return "ok";
    }

    @GetMapping("/set")
    public String setOps() {
        // todo ....
        return "ok";
    }

}
