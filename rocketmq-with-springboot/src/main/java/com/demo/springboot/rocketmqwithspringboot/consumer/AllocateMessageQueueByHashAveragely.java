package com.demo.springboot.rocketmqwithspringboot.consumer;

import org.apache.rocketmq.client.consumer.rebalance.AllocateMessageQueueAveragely;
import org.apache.rocketmq.common.MixAll;
import org.apache.rocketmq.common.message.MessageQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 消费
 * @date 2021/4/19 16:46
 * @see
 */
public class AllocateMessageQueueByHashAveragely extends AllocateMessageQueueAveragely {

    private final Logger log = LoggerFactory.getLogger(AllocateMessageQueueByHashAveragely.class);

    @Override
    public String getName() {
        return super.getName() + "ByIDHash";
    }

    @Override
    public List<MessageQueue> allocate(String consumerGroup, String currentCID,
                                       List<MessageQueue> mqAll, List<String> cidAll) {
        //解析queue id
        char idChar = consumerGroup.charAt(consumerGroup.length() - "ConsumerGroup".length() - 1);
        int id = Integer.parseInt(idChar + "");
        List<MessageQueue> submq = new ArrayList<MessageQueue>();
        //根据queue id分配相应的MessageQueue
        for (MessageQueue mq : mqAll) {
            if (mq.getQueueId() == idChar || mq.getTopic().startsWith(MixAll.RETRY_GROUP_TOPIC_PREFIX)) {
                submq.add(mq);
            }
        }
        if (submq.size() == 0) {
            log.warn("allocate err:" + consumerGroup + "," + currentCID + "," + cidAll + "," + mqAll);
        }
        return super.allocate(consumerGroup, currentCID, submq, cidAll);
    }
}
