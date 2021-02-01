# 工程简介
Spring session + redis 实现session 共享；（分布式session）



# 延伸阅读
关于session共享的方式有多种:
    
    (1)通过nginx的ip_hash，根据ip将请求分配到对应的服务器
    
    (2)基于关系型数据库存储
    
    (3)基于cookie存储
    
    (4)服务器内置的session复制域
    
    (5)基于nosql（memcache、redis都可以）

　　常用的就是1和5，下面研究第5种方式，基于nosql存储session。

　　其实实现原理也比较简单，在所有的请求之前配置一过滤器，在请求之前操作session，其实spring-session中真正起作用的session过滤器是:SessionRepositoryFilter。
    spring-session集成了redis与mongodb。
    
    
1.EnableRedisHttpSession注解？
EnableRedisHttpSession的核心是使用了Import方式导入了RedisHttpSessionConfiguration.class配置类。
该配置类通过@Bean注解，向Spring容器中注册了一个SessionRepositoryFilter
（SessionRepositoryFilter的依赖关系：SessionRepositoryFilter –> SessionRepository –> RedisTemplate –> RedisConnectionFactory）。
2.EnableDiscoveryClient注解？
@EnableDiscoveryClient：两层语义：1，本服务作为客户端注册到注册中心；2.开启服务发现功能，发现注册中心的服务列表
 
问题：还是注册不上consul ？ 啥问题？？？？
