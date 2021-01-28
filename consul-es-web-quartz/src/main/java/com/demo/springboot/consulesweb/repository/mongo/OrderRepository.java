package com.demo.springboot.consulesweb.repository.mongo;

import com.demo.springboot.consulesweb.entity.mongo.Order;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 订单仓储-mongo
 * @date 2021/1/26 19:20
 * @see ReactiveMongoRepository
 */
@Repository
public interface OrderRepository extends ReactiveMongoRepository<Order, String> {

}
