package com.demo.springboot.consulesweb.repository.redis;

import com.demo.springboot.consulesweb.entity.redis.ProductRedis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description redis 仓储: javaBean--> redis hash类型
 * @date 2021/1/28 17:23
 * @see
 */
@Repository
public interface ProductRepository extends CrudRepository<ProductRedis, Long> {

}
