package com.demo.springboot.rabbitmq.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description RabbitMQ工具类-rabbitTemplate封装
 * @date 2021/4/12 16:39
 * @see
 */
@Component
@Slf4j
public class RabbitMqUtilsWithRabbitTemplate {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RabbitAdmin rabbitAdmin;


    /**
     * @param name       队列名称
     * @param durable    队列是否要持久化
     * @param exclusive  队列是否是独占的，仅限于此连接
     * @param autoDelete 队列中消息消费完成时是否自动删除
     * @param arguments  其他参数，一般传null
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 创建队列
     * @date 2021/4/12 16:54
     */
    public void createQueue(String name, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments) {
        Queue queue = new Queue(name, durable, exclusive, autoDelete, arguments);
        String declareQueue = rabbitAdmin.declareQueue(queue);
        log.info("======>>>创建队列完成，队列：" + declareQueue);
    }

    /**
     * @param exchangeName 交换器名称
     * @param type         交换器类型：topic| direct|headers|fanout
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 创建交换器
     * @date 2021/4/12 17:01
     */
    public void createExchange(String exchangeName, String type) {
        Exchange exchange = null;
        if (ExchangeTypes.DIRECT.equalsIgnoreCase(type)) {
            exchange = ExchangeBuilder.directExchange(exchangeName).build();
        } else if (ExchangeTypes.FANOUT.equalsIgnoreCase(type)) {
            exchange = ExchangeBuilder.fanoutExchange(exchangeName).build();
        } else if (ExchangeTypes.TOPIC.equalsIgnoreCase(type)) {
            exchange = ExchangeBuilder.topicExchange(exchangeName).build();
        } else if (ExchangeTypes.HEADERS.equalsIgnoreCase(type)) {
            exchange = ExchangeBuilder.headersExchange(exchangeName).build();
        } else {
            exchange = new CustomExchange(exchangeName, type);
        }
        rabbitAdmin.declareExchange(exchange);
        log.info("===========>>创建exchange成功,交换器：" + exchange);
    }


    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 绑定队列和交换器
     * @date 2021/4/12 17:07
     */
    public void bindQueueAndExchange(String exchangeName, String queueName, String routingKey) {
//        String destination, Binding.DestinationType destinationType, String exchange, String routingKey, Map<String, Object> arguments
        // 绑定：交换器下游可以是交换器可以是队列
        Binding binding = new Binding(queueName, Binding.DestinationType.QUEUE, exchangeName, routingKey, null);
        rabbitAdmin.declareBinding(binding);
        log.info("=====>>>绑定成功：binding:" + binding);
    }

    /**
     * @param message 消息
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 发送消息到MQ
     * @date 2021/4/12 17:24
     */
    public void sendMsg(Object message, String exchangeName, String routingKey) {
        rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
        log.info("====>>>发送消息成功");
    }

    /**
     * @param queueName 队列名称
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 接收消息
     * @date 2021/4/12 17:32
     */
    public Object receiveMsg(String queueName) {
        Object convert = rabbitTemplate.receiveAndConvert(queueName);
        log.info("=====>>>接收消息成功：" + convert);
        return convert;
    }

}
