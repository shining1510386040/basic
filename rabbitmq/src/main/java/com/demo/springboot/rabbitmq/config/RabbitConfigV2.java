package com.demo.springboot.rabbitmq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description rabbitMQ配置类
 * @date 2021/1/11 12:12
 * @see
 */
@Configuration
@Slf4j
public class RabbitConfigV2 {


    @Value("${spring.rabbitmq.addresses}")
    private String hosts;

    @Value("${spring.rabbitmq.username}")
    private String userName;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Value("${spring.rabbitmq.virtual-host}")
    private String virtualHost;


    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description rabbitMQ 连接工厂
     * @date 2021/1/11 12:39
     */
    @Bean
    public ConnectionFactory connectionFactory() {

        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        cachingConnectionFactory.setAddresses(hosts);
        cachingConnectionFactory.setUsername(userName);
        cachingConnectionFactory.setPassword(password);
//        cachingConnectionFactory.setChannelCacheSize(channelCacheSize);
        //cachingConnectionFactory.setPort(port);
        cachingConnectionFactory.setVirtualHost(virtualHost);
        //设置连接工厂缓存模式：
        cachingConnectionFactory.setCacheMode(CachingConnectionFactory.CacheMode.CONNECTION);
        //缓存连接数
        cachingConnectionFactory.setConnectionCacheSize(5);
        //设置连接限制
        cachingConnectionFactory.setConnectionLimit(10);
        log.info("======>>>连接工厂CachingConnectionFactory设置完成，连接地址{}" + hosts);
        log.info("======>>>连接工厂CachingConnectionFactory设置完成，连接用户{}" + userName);
        return cachingConnectionFactory;
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description rabbitMQ 功能管理组件；
     * @date 2021/1/11 12:38
     */
    @Bean
    public RabbitAdmin rabbitAdmin() {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory());
        rabbitAdmin.setAutoStartup(true);
        rabbitAdmin.setIgnoreDeclarationExceptions(true);
        log.info("======>>>管理员RabbitAdmin设置完成");
        return rabbitAdmin;
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 消息接收，监听listener配置
     * @date 2021/1/11 12:34
     */
    @Bean
    public RabbitListenerContainerFactory listenerContainerFactory() {

        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        // 消息转换器
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        //最小消费者数量
        factory.setConcurrentConsumers(10);
        //最大消费者数量
        factory.setMaxConcurrentConsumers(10);
        //一个请求最大处理的消息数量
        factory.setPrefetchCount(10);
        //
        factory.setChannelTransacted(true);
        //默认不排队
        factory.setDefaultRequeueRejected(true);
        //手动确认接收到了消息
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        log.info("======>>>监听者SimpleRabbitListenerContainerFactory设置完成");
        return factory;
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description rabbitMQ 发送消息模板
     * @date 2021/1/11 12:27
     */
    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        rabbitTemplate.setMandatory(true);
        log.info("======>>>连接模板RabbitTemplate设置完成");
        return rabbitTemplate;
    }
}
