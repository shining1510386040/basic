package com.demo.springboot.web.cacheTraditional;

import com.google.common.collect.Maps;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 缓存管理器
 * @date 2020/12/28 12:34
 * @see
 */
@Component
public class CacheContext<T> {

    // 应用程序内存级缓存map
    private Map<String, T> cache = Maps.newConcurrentMap();

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 获取缓存
     * @date 2020/12/28 12:39
     */
    public T get(String key) {
        return cache.get(key);
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 添加或更新缓存
     * @date 2020/12/28 12:40
     */
    public void addOrUpdateCache(String key, T value) {
        cache.put(key, value);
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 按key清除缓存
     * @date 2020/12/28 12:41
     */
    public void evictCache(String key) {
        if (cache.containsKey(key)) {
            cache.remove(key);
        }
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 清除所有的缓存
     * @date 2020/12/28 12:43
     */
    public void clear() {
        cache.clear();
    }
}
