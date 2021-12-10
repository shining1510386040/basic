package com.demo.springboot.rocketmqwithspringboot.config;

import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.List;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 消息路由选择器
 * @date 2021/12/10 15:53
 * @see
 */
public class MyMessageQueueSelector implements MessageQueueSelector {

    @Override
    public MessageQueue select(List<MessageQueue> list, Message message, Object arg) {

        Integer id = Integer.valueOf(arg + "");
        System.out.println("====================>>>>>>" + id);
        String bodyJson = new String(message.getBody());
        String tags = message.getTags();
        System.out.println("===========>>>tags:" + tags);
        int index = id % list.size();
        return list.get(index);
    }
}
