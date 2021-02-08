package com.demo.springboot.web.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 自定义健康检查：检测器
 * @date 2021/2/8 11:18
 * @see
 */
@Component
public class CustomHealthInductor implements HealthIndicator {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * @author Wenyi Cao
     * @version 1.0
     * @description 测试：http://localhost:8080/actuator/health
     * @date 2021/2/8 11:41
     * @param 
     * @return 
     */
    @Override
    public Health health() {
        log.debug("正在检查redis...");
        try {
            // 测试redis 连通性,发送ping命令
            redisTemplate.execute(RedisConnection::ping);
            Health up = Health.up().withDetail("details", "redis健康检查通过").build();
            return up;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("redis配置项错误或网络超时");
            Health down = Health.down().withDetail("details", "redis健康检查不同通过").build();
            return down;
        }
    }
}
