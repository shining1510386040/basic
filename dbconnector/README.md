# 工程简介
springboot 多数据源的支持：结合mybatis实现，
    多租户数据分离（一个组织用一个数据库隔离开来，组织下用户数据共享）
    
    相关接口、类：
        spring-jdbc：AbstractRoutingDataSource
      （spring boot -jdbc 默认使用的数据源） com.zaxxer.hikari.HikariDataSource; HikariDataSource
       ibatis： PooledDataSource
    思路：主数据源master 配置各个自定义数据源的信息
spring- jta 分布式事务？
xa协议、两阶段提交的柔性事务方案；

# 延伸阅读

Spring 中事务的管理？
参考：https://www.jianshu.com/p/3938e7172443

PlatformTransactionManager：平台事务管理器接口；
    具体实现：
    jdbc的DataSourceTransactionManager  
    jpa的JpaTransactionManager
    rabbitMQ的RabbitTransactionManager
    hibernate的HibernateTransactionManager
    jta的JtaTransactionManager     --xa 事务、 多数据源、跨库事务、2阶段提交、柔性事务
    jms的JmsTransactionManager     
             
数据源：
    druid数据库连接池：
    xa数据源 DruidXADataSource
    jdbc数据源 DruidDataSource
