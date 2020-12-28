package com.demo.springboot.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 简单的rabbitmq消费者
 * @date 2020/12/21 12:41
 * @see
 */
@Component
public class SimpleRabbitmqConsumer {


    //    实现消费者主要用到注解@RabbitListener。@RabbitListener是一个功能强大的注解。
//    这个注解里面可以注解配置@QueueBinding、@Queue、@Exchange直接通过这个组合注解
//    一次性搞定多个交换机、绑定、路由、并且配置监听功能等。
//    @RabbitListener(bindings = @QueueBinding(value = @Queue("myQueue"), key = "mobi", exchange = @Exchange("myExchange")))

    // queuesToDeclare 自动创建队列；
    @RabbitListener(queuesToDeclare = @Queue("myqueue"))
    public void consume(String message) {
        System.out.println("===================>>>消费到了消息：");
        System.out.println(message);
    }

    // queues 在mq server管理端面板手动创建队列；
    @RabbitListener(queues = "rabbitmq-queue")
    public void consumeDefault(String message) {
        System.out.println("===================>>>消费到了消息：");
        System.out.println(message);
    }


}
