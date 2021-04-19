package com.demo.springboot.rocketmqwithspringboot.controller;

import com.demo.springboot.rocketmqwithspringboot.producer.BusinessProducer;
import com.demo.springboot.rocketmqwithspringboot.producer.DemoProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 测试controller
 * @date 2021/4/19 15:50
 * @see
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private DemoProducer producer;

    @Autowired
    private BusinessProducer businessProducer;

    @GetMapping("/produce")
    public void echo() throws Exception {
        producer.produce();
        producer.oneWay();
        producer.sync();
        producer.async();
        producer.send("123code", "我是小消息", "topic-async");

    }


    @GetMapping("/produceQueue")
    public void sendQueue() {
        for (int i = 1; i < 100; i++) {
            businessProducer.send("订单消息" + i, i);
        }
    }


}
