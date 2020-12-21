package com.demo.springboot.rabbitmq.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 消息发送到 queue的监听
 * @date 2020/12/21 18:59
 * @see
 */
@Service
public class ReturnCallBackListener implements RabbitTemplate.ReturnCallback {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        logger.info("return message:{}, code: {}, text: {}, exhcange:{}, routingkey:{}", message, replyCode, replyText,
                exchange, routingKey);
    }

}
