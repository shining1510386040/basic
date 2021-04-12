package com.demo.springboot.rabbitmq.util;

import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeoutException;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description rabbitMq工具类：java方式
 * @date 2021/4/12 10:35
 * @see
 */
public class RabbitMqUtils {

    private static Logger logger = LoggerFactory.getLogger(RabbitMqUtils.class);

    private ConnectionFactory factory;
    private Connection connection;
    private Channel channel;


    private static String rabbitUserName;
    private static String rabbitPassword;
    private static String rabbitVirtualHost;
    private static String rabbitHostName;
    private static String rabbitPort;

    static {
        Resource resource = new ClassPathResource("rabbit.properties", Object.class.getClassLoader());
        Properties properties = new Properties();
        try {
            properties.load(resource.getInputStream());
            rabbitUserName = properties.getProperty("userName");
            rabbitPassword = properties.getProperty("password");
            rabbitVirtualHost = properties.getProperty("virtualHost");
            rabbitHostName = properties.getProperty("hostName");
            rabbitPort = properties.getProperty("port");

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    /**
     * 初始化RabbitMq连接工具类
     *
     * @param host        主机
     * @param port        端口
     * @param userName    用户名
     * @param password    密码
     * @param virtualHost 虚拟主机
     * @throws IOException
     * @throws TimeoutException
     */
    public RabbitMqUtils(String host, int port, String userName, String password, String virtualHost) throws IOException, TimeoutException {
        if (StringUtils.isEmpty(host) || StringUtils.isEmpty(userName) || StringUtils.isEmpty(password) || StringUtils.isEmpty(virtualHost)) {
            this.factory = this.initConnectionFactory(rabbitHostName, Integer.valueOf(rabbitPort), rabbitUserName, rabbitPassword, rabbitVirtualHost);
        } else {
            this.factory = this.initConnectionFactory(host, port, userName, password, virtualHost);
        }

        // 创建与RabbitMQ服务器的TCP连接
        this.connection = connection == null ? this.factory.newConnection() : this.connection;
        this.channel = this.channel == null ? this.connection.createChannel() : this.channel;
    }

    /**
     * 初始化rabbitMq服务配置
     *
     * @param host        主机
     * @param port        端口
     * @param userName    用户名
     * @param password    密码
     * @param virtualHost 虚拟主机
     * @return
     */
    private ConnectionFactory initConnectionFactory(String host, int port, String userName, String password, String virtualHost) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);
        factory.setPort(port);
        factory.setUsername(userName);
        factory.setPassword(password);
        factory.setVirtualHost(virtualHost);
        return factory;
    }

    /**
     * @param queueName 队列名称
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 创建队列（持久化队列）
     * @date 2021/4/12 11:05
     */
    private void createQueue(String queueName) {

        try {

            this.channel.queueDeclare(queueName, true, false, false, null);
            logger.info("=====》》创建队列成功，queueName：" + queueName);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("=======>>创建队列失败");
        }
    }

    /**
     * @param exchangeName 交换器名称
     * @param type         交换器类型：direct| topic| headers|fanout
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 创建交换器(持久化的)
     * @date 2021/4/12 11:11
     */
    private void createExchange(String exchangeName, String type) {

        try {

            this.channel.exchangeDeclare(exchangeName, type, true);
            logger.info("=====》》创建队列成功，exchangeName：" + exchangeName);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("=======>>创建队列失败");
        }
    }

    /**
     * 队列和交换器绑定（会先生成队列和交换器然后在绑定）
     *
     * @param exchangeName 交换机名
     * @param queneName    队列名
     * @param routingKey   路由KEY
     * @param type         消息模式：FANOUT|TOPIC|DIRECT
     * @param durable      是否持久化
     * @param autoDelete   是否自动删除队列
     * @throws IOException
     */
    private void queueBind(String exchangeName, String queneName, String routingKey, BuiltinExchangeType type, boolean durable, boolean autoDelete) throws IOException {
        // 声明交换机类型：交换机，类型，持久化
        this.channel.exchangeDeclare(exchangeName, type, durable);
        if (queneName != null) {
            if (type != BuiltinExchangeType.DIRECT) {
                // 声明默认的队列：队列，持久化，声明独占队列（仅限于此连接），自动删除队列，队列的其他属性
                this.channel.queueDeclare(queneName, durable, false, autoDelete, null);
            }
            // 将队列与交换机绑定
            this.channel.queueBind(queneName, exchangeName, routingKey);
        }
    }

    /**
     * 发送消息到mq
     *
     * @param exchangeName 交换机名
     * @param queneName    队列名
     * @param routingKey   路由KEY
     * @param type         消息模式：FANOUT|TOPIC|DIRECT
     * @param msg          消息
     * @return
     */
    public boolean sendMq(String exchangeName, String queneName, String routingKey, BuiltinExchangeType type, String msg) {
        try {
            // 自动消费完成后删除队列
            this.queueBind(exchangeName, queneName, routingKey, type, true, true);
//            this.queueBind(exchangeName, queneName, routingKey, type, true, false);
            this.channel.basicPublish(exchangeName, routingKey, null, msg.getBytes());
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
        return true;
    }

    /**
     * 从MQ中拉取队列中的消息
     *
     * @param exchangeName    交换机名
     * @param queneName       队列名
     * @param routingKey      路由KEY
     * @param type            消息模式：FANOUT|TOPIC|DIRECT
     * @param headerInterface 回调实现
     * @throws IOException
     */
    public void pullMq(String exchangeName, String queneName, String routingKey, BuiltinExchangeType type, HeaderInterface headerInterface) throws IOException {
        if (queneName == null) {
            queneName = (type == BuiltinExchangeType.DIRECT) ? this.channel.queueDeclare().getQueue() : queneName;
        }
        String newsQueueName = queneName;
        this.queueBind(exchangeName, newsQueueName, routingKey, type, true, false);
        // 创建接收客户端，当有消息，则直接回调handleDelivery方法
        Consumer consumer = new DefaultConsumer(this.channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                logger.info("exchang:{}, routingKey:{}, queueName:{}, message:{}", envelope.getExchange(), envelope.getRoutingKey(), newsQueueName, message);
                headerInterface.execute(consumerTag, body);
            }
        };
        // channel绑定队列、消费者，autoAck为true表示一旦收到消息则自动回复确认消息
        this.channel.basicConsume(newsQueueName, true, consumer);
    }

    /**
     * 关闭连接通道
     *
     * @throws IOException
     * @throws TimeoutException
     */
    public void close() throws IOException, TimeoutException {
        if (this.channel != null) {
            this.channel.close();
            this.channel = null;
        }
        if (connection != null) {
            this.connection.close();
            this.connection = null;
        }
        this.factory = null;
    }

    /**
     * 函数式回调接口
     */
    @FunctionalInterface
    interface HeaderInterface {
        void execute(String consumerTag, byte[] body) throws IOException;
    }

    /**
     * 测试入口
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String[] words = new String[]{"props", "student", "build", "name", "execute"};

        RabbitMqUtils rabbitMqUtils2 = new RabbitMqUtils("127.0.0.1", 5672, "guest", "guest", "/");
//        int i = 0;
//
//        //FANOUT模式不需要routingKey，因此routingKey传空字符串（不要设置成null）
//        while (i < words.length) {
//            rabbitMqUtils2.sendMq("fanout-exchange2", "fanout-queue2", "", BuiltinExchangeType.FANOUT, words[i] + "," + CommonUtils.nextInt(1, 100));
//            i++;
//        }
//        System.out.println("发送结束");

        System.out.println("接收fanout消息");

        rabbitMqUtils2.pullMq("fanout-exchange1", "fanout-queue1", "", BuiltinExchangeType.FANOUT, (record, body) -> {
            String message = new String(body, "UTF-8");
            System.out.println(message);
        });

//        while (i < words.length) {
//            rabbitMqUtils2.sendMq("amq.topic", "test-topic", "topic-key", BuiltinExchangeType.TOPIC, words[i] + "," + RandomUtils.nextInt(1,100));
//            i++;
//        }
//        System.out.println("发送topic结束");
//
//        System.out.println("接收topic消息");
//        rabbitMqUtils2.pullMq("amq.topic", "test-topic", "topic-key", BuiltinExchangeType.TOPIC, (record, body) -> {
//            String message = new String(body, "UTF-8");
//            System.out.println(message);
//        });

        //生产者和消费者，分开两个测试类执行，否则会导制队列绑定失败
//        while (i < words.length) {
//            rabbitMqUtils2.sendMq("amq.direct", null,  "direct-key", BuiltinExchangeType.DIRECT, words[i] + "," + RandomUtils.nextInt(1,100));
//            i++;
//        }
//        System.out.println("发送direct结束");

//        System.out.println("接收direct消息");
//        rabbitMqUtils2.pullMq("amq.direct", null,  "direct-key", BuiltinExchangeType.DIRECT, (record, body) -> {
//            String message = new String(body, "UTF-8");
//            System.out.println(message);
//        });

        rabbitMqUtils2.close();
    }
}
