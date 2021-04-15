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
