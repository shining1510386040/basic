package com.demo.springboot.rocketmqwithspringboot.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 测试消费方
 * @date 2021/4/15 11:25
 * @see
 */
@Service
@Slf4j
@RocketMQMessageListener(topic = "topic-async", consumerGroup = "consumerGroup-test1")
public class DemoConsumer2 implements RocketMQListener<String> {

    @Override
    public void onMessage(String message) {
        byte[] bytes = message.getBytes(StandardCharsets.UTF_8);
        message = new String(bytes);
        log.error("================》》》received message: {}", message);
    }
}
