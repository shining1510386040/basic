package com.demo.springboot.rabbitmq.test;

import com.rabbitmq.client.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.text.BreakIterator;
import java.util.Properties;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 收消息-java方式；
 * @date 2021/1/8 11:06
 * @see
 */
public class RabbitConsumerInJava {

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

        String exchangeName = "exhange-in-java";
        String routingKey = "routingKey";

        // 1.建立连接
        Connection connection = connectionFactory.newConnection();
        // 2.获取channel 通道
        final Channel channel = connection.createChannel();

        channel.exchangeDeclare(exchangeName, "direct", true);
        // 获取queue名称:? amq.gen--3rOkF8c_sHToQ2CqcU8JA
//        String queueName = channel.queueDeclare().getQueue();
        String queueName = "queue-in-java";
        channel.queueBind(queueName, exchangeName, routingKey);
        // 接收消息
        boolean autoAck = false;
        while (true) {
            channel.basicConsume(queueName, autoAck, "", new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                    // 由信封获取
                    String routingKey = envelope.getRoutingKey();
                    long deliveryTag = envelope.getDeliveryTag();
                    String exchange = envelope.getExchange();
                    String contentType = properties.getContentType();

                    // ...消费消息 todo  body

                    System.out.println(new java.lang.String(body, "utf-8"));
                    // 手动提交
                    channel.basicAck(deliveryTag, false);
                }
            });

        }
    }


}
