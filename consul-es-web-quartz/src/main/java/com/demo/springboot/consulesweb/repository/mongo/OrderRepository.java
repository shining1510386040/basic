package com.demo.springboot.consulesweb.repository.mongo;

import com.demo.springboot.consulesweb.entity.mongo.Order;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Component;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 订单仓储-mongo，响应式编程
 * @date 2021/1/26 19:20
 * @see ReactiveMongoRepository
 */
@Component
public interface OrderRepository extends ReactiveMongoRepository<Order, String> {

}
