package com.demo.springboot.web.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 缓存配置：使用caffeine内存缓存
 * @date 2020/12/28 14:22
 * @see
 */
@Configuration
@EnableCaching
public class CacheConfig {

    // 默认缓存最大值
    public static final int DEFAULT_MAXSIZE = 50000;
    // 默认缓存过期时间 ttl
    public static final int DEFAULT_TTL = 10;

    /**
     * 定义缓存名称，超时时长（秒），最大容量
     * 每个cache缺省，10秒超时，最多缓存50000条数据，
     */
    public enum Caches {
        accounts,
        users,
        orders,
        getSomething,   // 缺省10s
        getAccountByUsername(15),
        getOtherthings(300, 1000);

        private int maxSize = DEFAULT_MAXSIZE;
        private int ttl = DEFAULT_TTL;

        public int getMaxSize() {
            return maxSize;
        }

        public int getTtl() {
            return ttl;
        }

        Caches(int maxSize, int ttl) {
            this.maxSize = maxSize;
            this.ttl = ttl;
        }

        Caches(int ttl) {
            this.ttl = ttl;
        }

        Caches() {
        }

    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 创建基于Caffeine的Cache Manager
     * @date 2020/12/28 14:31
     */
    @Bean
    @Primary
    public CacheManager caffeineCacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();

        ArrayList<CaffeineCache> caches = new ArrayList<>();
        // 枚举中的配置的缓存 转换为 list cache
        for (Caches c : Caches.values()) {

            Cache<Object, Object> cache = Caffeine.newBuilder().recordStats()
                    .expireAfterWrite(c.getTtl(), TimeUnit.SECONDS)
                    .maximumSize(c.getMaxSize())
                    .build();

            caches.add(new CaffeineCache(c.name(), cache));
        }

        cacheManager.setCaches(caches);

        return cacheManager;
    }

}
