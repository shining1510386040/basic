# 工程简介



# 延伸阅读

###RokcetMQ 相关接口
(类比RabbitMQ 的rabbitAdmin)
MQAdmin->
    MQProducer->DefaultMQProducer->TransactionMQProducer(支持事务的？)
    MQConsumer->DefaultMQConsumer
    
RocketMQTemplate（发消息）
RocketMQListener（收消息）

类比：
RabbitTemplate（收发消息）
###如何将消息发送到topic的指定的queue中去？
由于业务需要这样一种场景，将消息按照id(业务id)尾号发送到对应的queue中，并启动10个消费者(单jvm，10个消费者组)，
从对应的queue中集群消费，(假设有两个broker组成的集群)： 
###topic是一个逻辑概念，真正的存储是在message queue中；（类比kafka的topic：kafka中topic也是逻辑概念，真正给存储消息的partition）
一个mq集群的topic会分片在不同的broker上；而一个topic会划分为多个queue存储消息

通常会设置queue（partition）的数量大于消费者的数量：保证queue只能被一个消费者消费


在RocketMQ针对不同的场景，提供了集群消费与广播消费这两种模式。集群消费：一个消费组内的所有消费者共同消费一个主题中的队列，消费组内的每个消费者只消费一个topic的部分队列，但从消费组为维度，多个消费者最终能消费一个topic的全部消费，这就是负载均衡的思想。在RocketMQ中，队列负载的指导思想：
以消费组为维度，一个消费者能分配多个队列，但一个队列只会分配给一个消费者。故一个topic的队列数量直接决定了其支持的消费者的最大数，如果topic的队列数量小于消费者的数量，那部分消费者将无法消费消息。
广播模式：一个消费组内的每一个消费者都会消费topic中的所有消息，即topic 中的所有队列都会分配给消费组内的每一个消费者，其主要使用场景：刷新本地缓存。


#如何在发送消息时指定queue的数量呢？
未解决：
https://www.jianshu.com/p/ccdf6fc710b0


