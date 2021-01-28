package com.demo.springboot.consulesweb.controller;

import com.demo.springboot.consulesweb.entity.mongo.Order;
import com.demo.springboot.consulesweb.repository.mongo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 订单controller，响应式编程
 * @date 2021/1/26 19:22
 * @see
 */
@RestController
@RequestMapping("/mongo/order")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 查询单个
     * @date 2021/1/26 19:27
     */
    @GetMapping("/getOne/{id}")
    public Object getOne(@PathVariable String id) {
        // Request processing failed; nested exception is org.springframework.data.mongodb.
        // UncategorizedMongoDbException：认证问题，对应的db下要有对应的账号
        // db.createUser({user:'admin', pwd:'123456', roles:[{role:'userAdminAnyDatabase', db:'admin'}] })
        Mono<Order> orderMono = orderRepository.findById(id);
//        Order block = orderMono.block();
        return orderMono;
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 保存
     * @date 2021/1/26 19:26
     */
    @PostMapping("/save")
    public Object save(Order data) {
        Mono<Order> orderMono = orderRepository.save(data);
//        Order block = orderMono.block();
        return orderMono;
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 查询所有
     * @date 2021/1/26 19:28
     */
    @PostMapping("/list")
    public Object findAll() {
        Flux<Order> all = orderRepository.findAll();
//        Order block = orderMono.block();
        return all;
    }

    @PostMapping("/delete/{id}")
    public Object delete(@PathVariable String id) {
        Mono<Void> voidMono = orderRepository.deleteById(id);
        return voidMono;
    }


}
