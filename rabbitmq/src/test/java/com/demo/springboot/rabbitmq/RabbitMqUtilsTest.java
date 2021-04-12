package com.demo.springboot.rabbitmq;

import com.demo.springboot.rabbitmq.util.RabbitMqUtils;
import com.demo.springboot.rabbitmq.util.RabbitMqUtilsWithRabbitTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description rabbitMq工具测试
 * @date 2021/4/12 17:35
 * @see
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RabbitMqUtilsTest {

    @Autowired
    private RabbitMqUtilsWithRabbitTemplate rabbitMqUtils;

    @Test
    public void test1() {
        // autoDelete:消费完消息指定是否自动删除消息
        rabbitMqUtils.createQueue("util-queue", true, false, false, null);

    }


    @Test
    public void test2() {

        rabbitMqUtils.createExchange("util-exchange", "fanout");
        rabbitMqUtils.createExchange("util-exchange1", "direct");
        rabbitMqUtils.createExchange("util-exchange2", "topic");

    }

    @Test
    public void testBind() {
        // fanout :指定路由键为""
        rabbitMqUtils.bindQueueAndExchange("util-exchange", "util-queue", "");
    }

    @Test
    public void testSend() {
        rabbitMqUtils.sendMsg("我是消息", "util-exchange", "");
    }

    @Test
    public void testReceive() {
        Object o = rabbitMqUtils.receiveMsg("util-queue");
        System.out.println(o);
    }


}
