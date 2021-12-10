package com.demo.springboot.rocketmqwithspringboot.controller;

import com.demo.springboot.rocketmqwithspringboot.producer.BusinessProducer;
import com.demo.springboot.rocketmqwithspringboot.producer.DemoProducer;
import com.demo.springboot.rocketmqwithspringboot.producer.SimpleBatchProducer;
import com.demo.springboot.rocketmqwithspringboot.vo.ServiceResult;
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

    @Autowired
    private SimpleBatchProducer simpleBatchProducer;


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


    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 测试一次性发送多个消息到同一个topic
     * @date 2021/12/10 14:16
     */
    @GetMapping("/sendMsgBatch")
    public ServiceResult sendBatchToBroker() {
        return simpleBatchProducer.testSendBatch();
    }


    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 测试消息路由
     * @date 2021/12/10 15:19
     */
    @GetMapping("/testMsgRoute")
    public ServiceResult testMsgRoute() {
        return simpleBatchProducer.testMsgRoute();
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 测试发送消息时指定过滤tag和keys
     * @date 2021/12/10 15:43
     */
    @GetMapping("/testSendMsgWithTagAndKey")
    public ServiceResult testSendMsgWithTagAndKey() {
        return simpleBatchProducer.testSendMsgWithTagAndKey();
    }


}
