# 工程简介
    
    分库分表中间件shardingsphere 测试：


# 延伸阅读

    整体架构分为sharding-jdbc 模式和 sharding-proxy 模式
    
### 分片算法？



    自动分片算法
        取模分片算法
        哈希取模分片算法
        基于分片容量的范围分片算法
        基于分片边界的范围分片算法
        自动时间段分片算法
    标准分片算法
        行表达式分片算法
        时间范围分片算法
    复合分片算法
        复合行表达式分片算法
    Hint 分片算法
        Hint 行表达式分片算法
    自定义类分片算法

### shardingSphere的默认主键生成策略？
ShardingSphere提供灵活的配置分布式主键生成策略方式。在分片规则配置模块可配置每个表的主键生成策略，默认使用雪花算法（snowflake）生成64bit的长整型数据。

    

## 官方文档：

https://shardingsphere.apache.org/document/current/cn/user-manual/shardingsphere-jdbc/usage/sharding/spring-boot-starter/

逻辑表 user：水平拆分的表的总称。例：用户数据根据主键尾数拆分为2张表，分别是user0到user1，他们的逻辑表名为user。
真实表： 在分片的数据库中真实存在的物理表。即上个示例中的user0到user1
分片算法：Hint分片算法，对应HintShardingAlgorithm，用于处理使用Hint行分片的场景。需要配合HintShardingStrategy使用。
分片策略：行表达式分片策略，对应InlineShardingStrategy。使用Groovy的表达式，提供对SQL语句中的=和IN的分片操作支持，只支持单分片键。对于简单的分片算法，可以通过简单的配置使用，从而避免繁琐的Java代码开发，如: user$->{id % 2} 表示user表根据id模2，而分成2张表，表名称为user_0到user_1。
自增主键策略：通过在客户端生成自增主键替换以数据库原生自增主键的方式，做到分布式主键无重复。采用UUID.randomUUID()的方式产生分布式主键。或者 SNOWFLAKE
————————————————
版权声明：本文为CSDN博主「fei1234456」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/fei1234456/article/details/106963720/


====》》》4.1.1 文档
分片策略

包含分片键和分片算法，由于分片算法的独立性，将其独立抽离。真正可用于分片操作的是分片键 + 分片算法，也就是分片策略。目前提供5种分片策略。

    标准分片策略

对应StandardShardingStrategy。提供对SQL语句中的=, >, <, >=, <=, IN和BETWEEN AND的分片操作支持。StandardShardingStrategy只支持单分片键，提供PreciseShardingAlgorithm和RangeShardingAlgorithm两个分片算法。PreciseShardingAlgorithm是必选的，用于处理=和IN的分片。RangeShardingAlgorithm是可选的，用于处理BETWEEN AND, >, <, >=, <=分片，如果不配置RangeShardingAlgorithm，SQL中的BETWEEN AND将按照全库路由处理。

    复合分片策略

对应ComplexShardingStrategy。复合分片策略。提供对SQL语句中的=, >, <, >=, <=, IN和BETWEEN AND的分片操作支持。ComplexShardingStrategy支持多分片键，由于多分片键之间的关系复杂，因此并未进行过多的封装，而是直接将分片键值组合以及分片操作符透传至分片算法，完全由应用开发者实现，提供最大的灵活度。

    行表达式分片策略

对应InlineShardingStrategy。使用Groovy的表达式，提供对SQL语句中的=和IN的分片操作支持，只支持单分片键。对于简单的分片算法，可以通过简单的配置使用，从而避免繁琐的Java代码开发，如: t_user_$->{u_id % 8} 表示t_user表根据u_id模8，而分成8张表，表名称为t_user_0到t_user_7。

    Hint分片策略

对应HintShardingStrategy。通过Hint指定分片值而非从SQL中提取分片值的方式进行分片的策略。

    不分片策略

对应NoneShardingStrategy。不分片的策略。

### 遇到的问题？

1. entity 使用localdatetime 类型报错：？

#### 分布式主键生成方案？

    https://www.cnblogs.com/purple5252/p/14014734.html

   不同数据节点间生成全局唯一主键是个棘手的问题，
    一张逻辑表 t_order 拆分成多个真实表 t_order_n，然后被分散到不同分片库 
        db_0、db_1... ，各真实表的自增键由于无法互相感知从而会产生重复主键
        
   目前已经有了许多第三放解决方案可以完美解决这个问题，比如基于 UUID、SNOWFLAKE算法 、segment号段，使用特定算法生成不重复键，或者直接引用主键生成服务，像美团（Leaf）和 滴滴（TinyId）等。
   
   而sharding-jdbc 内置了两种分布式主键生成方案，UUID、SNOWFLAKE，不仅如此它还抽离出分布式主键生成器的接口，以便于开发者实现自定义的主键生成器，后续我们会在自定义的生成器中接入 滴滴（TinyId）的主键生成服务。
   
   ShardingSphere 相关接口：
   ShardingKeyGenerator：
        SnowflakeShardingKeyGenerator：雪花算法（默认雪花算法）
        UUIDShardingKeyGenerator：UUID方案