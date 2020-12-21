package com.demo.springboot.rabbitmq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 监听request-queue 队列的 消费者；
 * @date 2020/12/21 18:33
 * @see
 */
@Component
@RabbitListener(queues = "request-queue")
public class DemomqConsumer {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RabbitHandler
    public void consume1(String data) {
        logger.error("=============>消费：" + data);
    }
}
