package com.demo.springboot.web.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 操作redis的工具类，依赖spring IOC容器
 * @date 2020/12/24 17:11
 * @see
 */
@Component
public class RedisUtils {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    //============================通用功能===================

    /**
     * @param key  键
     * @param time 时间（单位秒）
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 指定缓存过期时间
     * @date 2020/12/24 17:14
     */
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            // 未知异常，网络。。。
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param key 键
     * @return 时间(单位 秒)，返回0代表永不过期
     * @author Wenyi Cao
     * @version 1.0
     * @description 获取key的过期时间
     * @date 2020/12/24 17:18
     */
    public long getExpire(String key) {
        return redisTemplate.getExpire(key);
    }

    /**
     * @param key 键
     * @return true 存在，false 不存在
     * @author Wenyi Cao
     * @version 1.0
     * @description 判断key是否存在
     * @date 2020/12/24 17:20
     */
    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param key 可传一个或多个
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 删除缓存
     * @date 2020/12/24 17:23
     */
    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    // ===============================操作字符串======================

    /**
     * @param key 键
     * @return 值
     * @author Wenyi Cao
     * @version 1.0
     * @description 普通缓存获取
     * @date 2020/12/24 17:27
     */
    public Object get(String key) {
        return StringUtils.isEmpty(key) ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * @param key    键
     * @param value 值
     * @return true 成功，false 失败
     * @author Wenyi Cao
     * @version 1.0
     * @description 普通缓存放入
     * @date 2020/12/24 17:29
     */
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param key   键
     * @param time  过期时间，单位 秒,此入参若小于等于0代表永不过期
     * @param value 值
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 普通缓存放入并设置过期时间
     * @date 2020/12/24 17:33
     */
    public boolean setWithExpire(String key, Object value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param key  键
     * @param step 递增步长，入参要大于0
     * @return 递增后的结果
     * @author Wenyi Cao
     * @version 1.0
     * @description 递增
     * @date 2020/12/24 17:40
     */
    public long incr(String key, long step) {
        if (step < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, step);
    }

    /**
     * @param key  键
     * @param step 步长，入参大于0
     * @return 递减后结果
     * @author Wenyi Cao
     * @version 1.0
     * @description 递减
     * @date 2020/12/24 17:44
     */
    public long decr(String key, long step) {

        if (step < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        return redisTemplate.opsForValue().decrement(key, step);
    }

    // ===========================hash的操作===================

    /**
     * @param key  键，入参不能为null
     * @param item 项，入参不能为null
     * @return hash中item项的值
     * @author Wenyi Cao
     * @version 1.0
     * @description 获取hash的一个field的值
     * @date 2020/12/24 17:48
     */
    public Object hget(String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }

    /**
     * @param key 键
     * @return hash的所有键值
     * @author Wenyi Cao
     * @version 1.0
     * @description 获取hash的所有键值
     * @date 2020/12/24 17:51
     */
    public Map<Object, Object> hmget(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * @param key 键
     * @param map hash对象
     * @return true 成功，false失败
     * @author Wenyi Cao
     * @version 1.0
     * @description 存储一个hash
     * @date 2020/12/24 17:54
     */
    public boolean hmset(String key, Map<Object, Object> map) {

        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param key  键
     * @param map  hash值
     * @param time 时间，单位（秒）,要求入参大于0
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 存储一个hash值并设置过期时间
     * @date 2020/12/24 17:58
     */
    public boolean hmsetWithExpire(String key, Map<Object, Object> map, long time) {

        if (hmset(key, map)) {
            return expire(key, time);
        }
        return false;
    }

    /**
     * @param key   键
     * @param item  项
     * @param value 值
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 向一个hash的item项添加数据，若item不存在则创建，存在则覆盖其value值
     * @date 2020/12/24 18:04
     */
    public boolean hset(String key, String item, Object value) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * @param key  键
     * @param item item项，不能为null，可一个或多个
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 删除hash中的item项
     * @date 2020/12/24 18:07
     */
    public void hdel(String key, Object... item) {
        redisTemplate.opsForHash().delete(key, item);
    }

    /**
     * @param key  键
     * @param item 项
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 判断一个hash中是否包含某一项
     * @date 2020/12/24 18:10
     */
    public boolean hHasKey(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    /**
     * @param key  键
     * @param item 项
     * @param by   步长,入参要求大于0
     * @return item项的value递增后的值
     * @author Wenyi Cao
     * @version 1.0
     * @description hash递增，若item项不存在则创建
     * @date 2020/12/24 18:12
     */
    public double hincr(String key, String item, double by) {

        return redisTemplate.opsForHash().increment(key, item, by);
    }

    /**
     * @param key  键
     * @param item 项
     * @param by   步长,入参要求大于0
     * @return item项的value递减后的值
     * @author Wenyi Cao
     * @version 1.0
     * @description hash递减，若item项不存在则创建
     * @date 2020/12/24 18:12
     */
    public double hdecr(String key, String item, double by) {

        return redisTemplate.opsForHash().increment(key, item, -by);
    }

    // ========================set集合的操作==============================

    /**
     * @param key 键
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 获取set集合
     * @date 2020/12/24 18:22
     */
    public Set<Object> sGet(String key) {
        try {
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param key   键
     * @param value 要判断的值
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 判断一个值是否在set集合中
     * @date 2020/12/24 18:26
     */
    public boolean sIsMember(String key, Object value) {
        try {
            return redisTemplate.opsForSet().isMember(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param key    键
     * @param values 值，一个或多个，不能为null
     * @return 成功的个数
     * @author Wenyi Cao
     * @version 1.0
     * @description 将一个值放入set集合中
     * @date 2020/12/24 18:28
     */
    public long sSet(String key, Object... values) {
        try {
            return redisTemplate.opsForSet().add(key, values);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * @param key 键
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 获取set集合汇总元素的个数
     * @date 2020/12/24 18:31
     */
    public long sGetMemborSize(String key) {
        try {
            return redisTemplate.opsForSet().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * @param key    键
     * @param values 元素值，可为一个或多个
     * @return 移除的个数
     * @author Wenyi Cao
     * @version 1.0
     * @description 移除set中元素
     * @date 2020/12/24 18:33
     */
    public long sRemove(String key, Object... values) {

        try {
            Long remove = redisTemplate.opsForSet().remove(key, values);
            return remove;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }

    // ================================list 列表的操作=====================

    /**
     * @param key   键
     * @param end   结束位置
     * @param start 开始位置 左闭右开，从0开始
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 获取list缓存中的内容
     * @date 2020/12/24 18:37
     */
    public List<Object> lGet(String key, long start, long end) {
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param key 键
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 获取list缓存的长度
     * @date 2020/12/24 18:39
     */
    public long lGetSize(String key) {

        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * @param key   键
     * @param index 索引位置，index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 获取list缓存中index位置的值
     * @date 2020/12/24 18:41
     */
    public Object lgetByIndex(String key, long index) {
        try {
            return redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param key   键
     * @param value 值
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 在list缓存右侧插入值
     * @date 2020/12/24 18:46
     */
    public boolean lRightPush(String key, Object value) {

        try {
            redisTemplate.opsForList().rightPush(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * @param key   键
     * @param value list值
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description list缓存，右侧追加list
     * @date 2020/12/24 18:49
     */
    public boolean lSet(String key, List<Object> value) {

        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * @param key   键
     * @param value 值
     * @param index 索引位置
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 根据索引修改list缓存中值
     * @date 2020/12/24 18:52
     */
    public boolean lUpdateIndex(String key, long index, Object value) {

        try {
            redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param key   键
     * @param value 值
     * @param count 移除的个数
     * @return 移除的个数
     * @author Wenyi Cao
     * @version 1.0
     * @description 移除N个Value值
     * @date 2020/12/24 18:55
     */
    public long lRemove(String key, long count, Object value) {

        try {
            Long remove = redisTemplate.opsForList().remove(key, count, value);
            return remove;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
