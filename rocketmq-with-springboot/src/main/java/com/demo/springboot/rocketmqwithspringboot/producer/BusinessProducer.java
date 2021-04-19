package com.demo.springboot.rocketmqwithspringboot.producer;

import com.demo.springboot.rocketmqwithspringboot.vo.ServiceResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 发送消息到指定的topic的queue
 * @date 2021/4/19 16:37
 * @see
 */
@Slf4j
@Service
public class BusinessProducer {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;


    /**
     * @param msg     消息
     * @param orderId 订单id
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 发送消息到指定topic的队列
     * @date 2021/4/19 16:51
     */
    public ServiceResult send(Object msg, Integer orderId) {
        MessageQueueSelector selector = new IDHashMessageQueueSelector();
        rocketMQTemplate.setMessageQueueSelector(selector);
        SendResult sendResult = rocketMQTemplate.syncSend("topic-snyc", msg);
        ServiceResult ret = new ServiceResult("200", "发送成功");
        ret.setData(sendResult);
        return ret;
    }
}
