package com.demo.springboot.consulesweb.entity.redis;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 产品-redis 仓储实体
 * <p>
 * javaBean --> redis 中的hash类型
 * <p>
 * 在redis中的存储key为：product:id
 * value为hash类型
 * @date 2021/1/26 10:19
 * @see
 */
@Data
@RedisHash("product")
public class ProductRedis {

    @Id
    private long id;
    private String productName;

    private String productDesc;

    private double price;

}
