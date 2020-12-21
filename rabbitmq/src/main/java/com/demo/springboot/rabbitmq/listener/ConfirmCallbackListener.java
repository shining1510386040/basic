package com.demo.springboot.rabbitmq.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 生产者判断消息成功发送到 broker得监听
 * @date 2020/12/21 19:06
 * @see
 */
@Service
public class ConfirmCallbackListener implements RabbitTemplate.ConfirmCallback {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        logger.info("ConfirmCallbackListener CorrelationData: {}, ack: {}, cause: {}", correlationData, ack, cause);
    }
}
