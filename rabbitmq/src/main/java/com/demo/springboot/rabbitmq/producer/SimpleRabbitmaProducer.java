package com.demo.springboot.rabbitmq.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 简单的rabbitmq 生产者，发消息的；
 * @date 2020/12/21 12:32
 * @see
 */
@Component
public class SimpleRabbitmaProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    public void produce(String data) {
        String message = new Date() + "I am producer :" + data;
        System.out.println("==================>>>>> 发送了一条消息");
        // convertAndsend: 发送消息，可指定 交换器，路由键，消息内容
        rabbitTemplate.convertAndSend("rabbitmq-queue", message);

    }

    public void producenew(String data) {
        String message = new Date() + "I am producer :" + data;
        System.out.println("==================>>>>> 发送了一条消息");
        // convertAndsend: 发送消息，可指定 交换器，路由键，消息内容
        rabbitTemplate.convertAndSend("rabbitmq-queue", message);

    }


}
