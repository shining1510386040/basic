# 工程简介
    测试Redisson功能；

    Redisson是一个在Redis的基础上实现的Java驻内存数据网格（In-Memory Data Grid）。它不仅提供了一系列的分布式的Java常用对象，还提供了许多分布式服务。其中包括(BitSet, Set, Multimap, SortedSet, Map, List, Queue, BlockingQueue, Deque, BlockingDeque, Semaphore, Lock, AtomicLong, CountDownLatch, Publish / Subscribe, Bloom filter, Remote service, Spring cache, Executor service, Live Object service, Scheduler service) Redisson提供了使用Redis的最简单和最便捷的方法。Redisson的宗旨是促进使用者对Redis的关注分离（Separation of Concern），从而让使用者能够将精力更集中地放在处理业务逻辑上。
    
    关于Redisson项目的详细介绍可以在官方网站找到。
    
    每个Redis服务实例都能管理多达1TB的内存。
    
    能够完美的在云计算环境里使用，并且支持AWS ElastiCache主备版，AWS ElastiCache集群版，Azure Redis Cache和阿里云（Aliyun）的云数据库Redis版
    
    以下是Redisson的结构：
    
        Redisson作为独立节点 可以用于独立执行其他节点发布到分布式执行服务 和 分布式调度任务服务 里的远程任务。
    
    如果你现在正在使用其他的Redis的Java客户端，那么Redis命令和Redisson对象匹配列表 能够帮助你轻松的将现有代码迁徙到Redisson框架里来。
    
    Redisson底层采用的是Netty 框架。支持Redis 2.8以上版本，支持Java1.6+以上版本。
    
    欢迎试用高性能Redisson PRO版。

# 延伸阅读

    Redisson参考手册：https://github.com/redisson/redisson/wiki/%E7%9B%AE%E5%BD%95
    
## RBloomFilter 布隆过滤器，判断集合中是否存在某个元素（大数据量，允许容错）

    public interface RBloomFilter<T> extends RExpirable {
     
        boolean tryInit(long var1, double var3);  //初始化布隆过滤器，var1表示大小，var3表示容错率
     
        boolean add(T var1);                      //添加对象
        boolean contains(T var1);                 //判断对象是否存在
     
        long getExpectedInsertions();             //返回预计插入数量
        double getFalseProbability();             //返回容错率
        int getHashIterations();                  //返回hash函数个数
     
        long count();                             //对象插入后，估计插入数量
        long getSize();                           //布隆过滤器位数组的大小
     
    }
