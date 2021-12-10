package com.demo.springboot.rocketmqwithspringboot.producer;

import com.alibaba.fastjson.JSON;
import com.demo.springboot.rocketmqwithspringboot.config.MyMessageQueueSelector;
import com.demo.springboot.rocketmqwithspringboot.entity.Order;
import com.demo.springboot.rocketmqwithspringboot.vo.ServiceResult;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 消息批量发送：
 * 将同一个主题的消息批量打包发送到消息服务器broker，
 * 减少网络调用次数，提高网络传输率
 * @date 2021/12/10 11:43
 * @see
 */
@Service
public class SimpleBatchProducer {


    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description java 程序写法
     * @date 2021/12/10 11:55
     */
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {

        // 批量发送生产者组
        DefaultMQProducer producer = new DefaultMQProducer("batchSendGroup");
        // 设置消息服务器broker地址
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.start();

        // 准备消息
        String topic = "batchTest";
        List<Message> messageList = new ArrayList<>();
        // Message:主题，过滤tag标签，？，消息体内容
        messageList.add(new Message(topic, "Tag", "orderId001", "订单1内容".getBytes()));
        messageList.add(new Message(topic, "Tag", "orderId002", "订单2内容".getBytes()));
        messageList.add(new Message(topic, "Tag", "orderId003", "订单3内容".getBytes()));

        SendResult send = producer.send(messageList);

        // send(Message msg, MessageQueueSelector selector, Object arg)
//        producer.send();
        System.out.println(send);
        producer.shutdown();

    }


    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 测试批量发送消息到mq服务器
     * @date 2021/12/10 11:57
     */
    public ServiceResult testSendBatch() {
        // 异步发送，保证送达
//        rocketMQTemplate.asyncSend();
        // 发出即可，可能会丢失，不到的消息服务器broker
//        rocketMQTemplate.sendOneWay();
        // 同步发送，保证送达 ack机制？
        // 同步发送多个，到相同的主题
        String topic = "orderTopicBatch";

        // 准备消息
        List<Order> orderList = new ArrayList<>();
        // 注：使用rocketMQTemplat 需使用spring封装的Message消息，不能使用rocketMQ封装的原生，
        // 否则会当做一次消息的payload发送出去，不是批量
        List<Message> messageList = new ArrayList<>();
        List<org.springframework.messaging.Message<Order>> msgList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Order order = new Order("order00" + i, "订单1" + i, "苹果手机订单" + i);
            orderList.add(order);
            // rocketMQ 封装的消息：主题，过滤tag、key、消息体
            Message message = new Message(topic, "order-tag", "", JSON.toJSONString(order).getBytes());
            messageList.add(message);

            msgList.add(new GenericMessage<>(order));
        }
//        // syncSend(String destination, Object payload) 这是发送了一个消息，不是发了多个消息
//        // 只是消息体 为list集合而已
//        SendResult sendResult = rocketMQTemplate.syncSend(topic, orderList);
//        rocketMQTemplate.syncSend(topic, messageList, 2000);

//        syncSend(String destination, Collection<Message<?>> messages, long timeout)
        SendResult sendResult = rocketMQTemplate.syncSend(topic, (Collection) msgList, 2000);

        System.out.println(sendResult);
        ServiceResult ret = new ServiceResult("200", "发送成功");
        ret.setData(sendResult);
        return ret;
    }


    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 测试：消息发送队列子选择；
     * 消息发送到指定的消费队列去，默认轮询算法
     * @date 2021/12/10 14:55
     */
    public ServiceResult testMsgRoute() {

        // 指定消息路由算法
//        rocketMQTemplate.setMessageQueueSelector(new MessageQueueSelector() {
//            @Override
//            public MessageQueue select(List<MessageQueue> list, Message message, Object arg) {
//
//                Integer id = Integer.valueOf(arg + "");
//                System.out.println("====================>>>>>>" + id);
////                String bodyJson = new String(message.getBody());
//                int index = id % list.size();
//                return list.get(index);
//            }
//        });

        // 不好使？？
        rocketMQTemplate.setMessageQueueSelector(new MyMessageQueueSelector());

        String topic = "OrderRouteTest";

        // 消息
        for (int i = 0; i < 10; i++) {
            Order order = new Order("1" + i, "我是订单1" + i, "订单1" + i);

            // 顺序消息
            // 消息路由的hashkey
            rocketMQTemplate.syncSendOrderly(topic, order, order.getOrderId());
        }

        return null;
    }


    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 测试使用RocketMQTemplate 发送消息指定tag和keys
     * @date 2021/12/10 15:42
     */
    public ServiceResult testSendMsgWithTagAndKey() {

        Order order = new Order("111", "我是测试订单", "订单1");

        org.springframework.messaging.Message message = MessageBuilder
                // 消息体
                .withPayload(order)
                // 消息头
                .setHeader("KEYS", UUID.randomUUID())
                .build();
        // 目的地 格式：topicName:tagName
        String dest = "OrderTopicWithTagAndKeys:orderTag";
        SendResult result = rocketMQTemplate.syncSend(dest, message);

        ServiceResult ret = new ServiceResult("200", "操作成功");
        ret.setData(result);
        return ret;
    }


}
