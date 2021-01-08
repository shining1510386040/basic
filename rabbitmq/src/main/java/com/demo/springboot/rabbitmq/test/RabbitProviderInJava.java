package com.demo.springboot.rabbitmq.test;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Properties;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 发消息-java方式；
 * @date 2021/1/8 11:06
 * @see
 */
public class RabbitProviderInJava {

    private static String userName;
    private static String password;
    private static String virtualHost;
    private static String hostName;
    private static String port;

    static {
        Resource resource = new ClassPathResource("rabbit.properties", Object.class.getClassLoader());
        Properties properties = new Properties();
        try {
            properties.load(resource.getInputStream());
            userName = properties.getProperty("userName");
            password = properties.getProperty("password");
            virtualHost = properties.getProperty("virtualHost");
            hostName = properties.getProperty("hostName");
            port = properties.getProperty("port");

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public static void main(String[] args) throws Exception {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setUsername(userName);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(virtualHost);
        connectionFactory.setHost(hostName);
        connectionFactory.setPort(Integer.valueOf(port));

        String exchangeName = "exchange-in-java";
        String queueName = "queue-in-java";
        String routingKey = "routingKey";


        // 1.建立连接
        Connection connection = connectionFactory.newConnection();
        // 2.获取channel 通道
        Channel channel = connection.createChannel();
        // 3.绑定 exchange 和queue
        // 声明交换器，
        AMQP.Exchange.DeclareOk direct = channel.exchangeDeclare(exchangeName, "direct", true);
        // 声明队列，
        AMQP.Queue.DeclareOk declareOk = channel.queueDeclare(queueName, true, false, false, null);
        // 绑定交换器、队列、路由键
        channel.queueBind(queueName, exchangeName, routingKey);
        // 4.发消息
        String messagge = "大家好，我是rabbitMQ，一种消息中间件";
        AMQP.BasicProperties.Builder propsBuilder = new AMQP.BasicProperties.Builder();
        propsBuilder.deliveryMode(2); // persistent
        propsBuilder.priority(0); // normal
        channel.basicPublish(exchangeName, routingKey, propsBuilder.build(), messagge.getBytes());
        // 5.关闭连接
        channel.close();
        connection.close();
        // 阻塞。。
        System.in.read();
    }


}
