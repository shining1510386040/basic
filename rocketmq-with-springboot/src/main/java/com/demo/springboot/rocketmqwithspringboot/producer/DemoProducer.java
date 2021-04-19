package com.demo.springboot.rocketmqwithspringboot.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 测试消息生产端
 * @date 2021/4/15 11:27
 * @see
 */
@Slf4j
@Service
public class DemoProducer {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

//    当发送的消息不重要时，采用one-way方式，以提高吞吐量；
//    当发送的消息很重要是，且对响应时间不敏感的时候采用sync方式;
//    当发送的消息很重要，且对响应时间非常敏感的时候采用async方式；

    /**
     * 发送消息
     */
    public void produce() {
        rocketMQTemplate.convertAndSend("test-topic-new1", "Hello, World!");
        rocketMQTemplate.send("test-topic-new1", MessageBuilder.withPayload("Hello, World! I'm from spring message").build());
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 直达
     * @date 2021/4/19 16:07
     */
    public void oneWay() {
        rocketMQTemplate.sendOneWay("topic-onway", "我是oneway消息");
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 同步
     * @date 2021/4/19 16:07
     */
    public void sync() {
        rocketMQTemplate.syncSend("topic-sync", "我是同步消息");
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 异步
     * @date 2021/4/19 16:07
     */
    public void async() {
        rocketMQTemplate.asyncSend("topic-async", "我是异步消息", new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                // 发送消息成功回调
                log.info("send successful");
            }

            @Override
            public void onException(Throwable throwable) {
                // 发送消息失败回调
                log.info("send fail; {}", throwable.getMessage());
            }
        });

    }

    /**
     * @param businessCode 业务代码
     * @param ms           消息
     * @param springTopic  topic主题
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 异步发送顺序消息
     * @date 2021/4/19 16:15
     */
    @Async
    public SendResult send(String businessCode, String ms, String springTopic) throws Exception {
        //生成message类型
        Message<byte[]> message = MessageBuilder.withPayload(ms.getBytes(StandardCharsets.UTF_8)).build();
        SendResult sendResult = rocketMQTemplate.syncSendOrderly(springTopic, message, springTopic);
        log.info("{},mq send business_code: {}", springTopic, businessCode);
        log.info("mq send message: {}", ms);
        log.info("mq send result: {}", sendResult);
        return sendResult;
    }
}