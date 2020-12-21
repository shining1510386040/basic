package com.demo.springboot.rabbitmq.controller;

import com.demo.springboot.rabbitmq.producer.SimpleRabbitmaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description TODO
 * @date 2020/12/21 12:51
 * @see
 */
@RestController
@RequestMapping("/rabbitmq")
public class RabbitmqController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SimpleRabbitmaProducer producer;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/send")
    public String serviceProcess(){
        // ....业务处理
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 发消息到mq
        producer.produce("我是shining，我很帅！");
        return "success";
    }

    @GetMapping("/sendMsg")
    public void sendMsg(@RequestParam String topic,@RequestParam String route,@RequestParam String message){

        rabbitTemplate.convertAndSend(topic,route,message);
        logger.error("=============>>发送消息ok");

    }

}
