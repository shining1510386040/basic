package com.demo.springboot.web.controller;

import com.demo.springboot.web.entity.OrderInfo;
import com.demo.springboot.web.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

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

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 对hash的操作
     * @date 2020/12/28 10:29
     */
    @GetMapping("/hash")
    public String hashOps() {

        String key = "redis:hash:orderId";
        // 1.添加
        Map<Object, Object> data = new HashMap<>(8);
        data.put("id", 123);
        data.put("orderName", "苹果手机订单");
        data.put("price", 234.6f);
        redisUtils.hmset(key, data);
        // 2.增加item
        redisUtils.hset(key, "desc", "我是苹果手机订单。。。");
        // 3.判断key是否存在
        boolean b = redisUtils.hasKey(key);
        // 4.判断item项是否存在
        boolean desc = redisUtils.hHasKey(key, "desc");
        // 5.删除item项
        redisUtils.hdel(key, "desc", "desc1");
        // 6.删除key
        redisUtils.del(key);
        redisUtils.hmset(key, data);
        // 7.获取hash值
        Map<Object, Object> hmget = redisUtils.hmget(key);
        System.out.println(hmget);
        // 8.获取item项的值
        Object orderName = redisUtils.hget(key, "orderName");
        System.out.println(orderName);
        return "ok";
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 对list的操作
     * @date 2020/12/28 10:42
     */
    @GetMapping("/list")
    public String listOps() {

        String key = "redis:list:orderId";
        // 1.添加list
        List data = Arrays.asList(1, 2, 34, 5, 6, 789, 34);
        boolean b = redisUtils.lSet(key, data);
        // 2.右侧追加
        boolean b1 = redisUtils.lRightPush(key, "9999");
        // 3.按index更新
        boolean b2 = redisUtils.lUpdateIndex(key, 4, "4-zhagnsan");
        // 4.获取长度
        long l = redisUtils.lGetSize(key);
        // 5.获取值
        List<Object> objects = redisUtils.lGet(key, 0, l);
        System.out.println(objects);
        // 6.获取index位置值
        Object o = redisUtils.lgetByIndex(key, 5);
        System.out.println(o);
        // 7.删除指定个数的值
        long l1 = redisUtils.lRemove(key, 2, 34);


        return "ok";
    }

    /**
     * @author Wenyi Cao
     * @version 1.0
     * @description set集合操作
     * @date 2020/12/28 10:50
     * @param
     * @return
     */
    @GetMapping("/set")
    public String setOps() {

        String key = "redis:set:orderId";
        Object[] data = {"zhgnsan","lisi","wangwu","zhaoliu"};
        // 1.添加  一个或多个
        long l = redisUtils.sSet(key, data);
        redisUtils.sSet(key,"desc-1");
        redisUtils.sSet(key,"desc-3");
        // 2.判断是否为集合中元素
        boolean lisi = redisUtils.sIsMember(key, "lisi");
        // 3.获取set中所有元素值
        Set<Object> objects = redisUtils.sGet(key);
        System.out.println(objects);
        // 4.获取集合元素个数
        long l1 = redisUtils.sGetMemborSize(key);
        // 5.移除set中元素
        long l2 = redisUtils.sRemove(key, "wangwu", "zhagnsan");

        return "ok";
    }

}
