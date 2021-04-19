package com.demo.springboot.rocketmqwithspringboot.producer;

import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.List;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 按业务id尾数，hash到指定的queue：例如订单id
 * @date 2021/4/19 16:41
 * @see
 */
public class IDHashMessageQueueSelector implements MessageQueueSelector {


    @Override
    public MessageQueue select(List<MessageQueue> mqs, Message msg,
                               Object arg) {
        // 订单id：整形，例如1232442
        int id = Integer.parseInt(arg.toString());
        // 消息的总数
        int size = mqs.size();
        int index = id % size;
        return mqs.get(index);
    }
}
