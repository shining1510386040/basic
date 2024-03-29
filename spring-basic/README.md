# 工程简介
    
    spring一些基础的测试：
    1.spring事务:aop、动态代理、事务传播特性、ACID、编程式+声明式使用事务
    2.spring Bean的管理：反射原理、IOC、依赖注入，bean的生命周期，applicationContext
    3.spring 的定时任务：@scheduling
    4.spring 动态数据源：DynamicDataSource接口
    


# 延伸阅读

### spring事务？
    1.数据库事务？
        1.1事务特性：ACID
            ⑴ 原子性（Atomicity）
            　　原子性是指事务包含的所有操作要么全部成功，要么全部失败回滚。
                因此事务的操作如果成功就必须要完全应用到数据库，如果操作失败则不能对数据库有任何影响。
            ⑵ 一致性（Consistency）
            　　一致性是指事务必须使数据库从一个一致性状态变换到另一个一致性状态.
                也就是说一个事务执行之前和执行之后都必须处于一致性状态。
            　　拿转账来说，假设用户A和用户B两者的钱加起来一共是5000，那么不管A和B之间如何转账，
                转几次账，事务结束后两个用户的钱相加起来应该还得是5000，这就是事务的一致性。
            ⑶ 隔离性（Isolation）
            　　隔离性是当多个用户并发访问数据库时，比如操作同一张表时，
                数据库为每一个用户开启的事务，不能被其他事务的操作所干扰，
                多个并发事务之间要相互隔离。
            
            　　即要达到这么一种效果：对于任意两个并发的事务T1和T2，在事务T1看来，T2要么在T1开始之前就已经结束，要么在T1结束之后才开始，这样每个事务都感觉不到有其他事务在并发地执行。
            ⑷ 持久性（Durability）
            　　持久性是指一个事务一旦被提交了，那么对数据库中的数据的改变就是永久性的，
                即便是在数据库系统遇到故障的情况下也不会丢失提交事务的操作。
                
            注：
            A:原子性，一个事务的所有操作，要么全部成功，要么全部失败回滚；
            C:一致性，事务的操作必须由一个一致性状态到达另一个一致性状态；
            I:隔离性，多个并发事务之间相互独立，互不影响；
            D:持久性，事务一旦提交，那么对db的更改就是永久的；
        
        1.2数据库事务的隔离级别？
            读未提交：（一个事务可以读到另一个事务未提交的数据）RU，可发生脏读、不可重复读、幻读。
            读已提交：（一个事务可以读到另一事务已提交的数据）RC，可发生不可重复读、幻读。
            可重复读：（一个事务执行期间读取到的数据都是一样的，不受其他事务影响）RR，可发生幻读。
            串行化：（语句执行按照串行执行，性能受到很大影响不会生产上是使用）可防止幻读；
            注：mysql默认是可重复读的隔离级别，采用的MVCC多版本并发控制机制，按照记录行上增加两个隐藏列：事务id和回滚指针
            来实现行记录的多个版本；每次读取都是快照读；
        1.3Spring事务的传播特性？
            一、支持当前事务的：
                required：（必须的），若当前有事务则使用当前事务、若当前没有事务则创建一个事务执行；
                supports：（支持的），若当前有事务则使用事务执行后续操作，若没有事务则以非事务的方式执行；
                mandatory：（强制的），若当前没有事务则执行时抛出异常；（事务状态异常）
            二、不支持当前事务的：
                requires_new:(隔离的)，需要一个新的事务来执行后续，当前事务挂起；
                not_supported:(不支持事务的)，以非事务的方式执行，存在事务则挂起；
                never：（非事务），不使用事务       
             三、嵌套事务：
                nested：？
     
 ###JdbcTemplate操作详解？
          1. execute：可以执行所有SQL语句，一般用于执行DDL语句。
              执行sql语句，例如创建表。
          2.  update：用于执行INSERT、UPDATE、DELETE等DML语句。
              执行对表数据有更改的操作；
          3.  queryXxx：用于DQL数据查询语句。
               执行查询语句；
          4. batchUpdate():批量执行，批量插入 
          
 ### Spring中bean的生命周期？
    getBean()--> 实例化-->填充属性-->初始化；
    
    getBean()-->createBeanInstance()-->populateBean()-->{..}-->initialBean()
    
 
 ### Spring中Bean的循环依赖问题？（单例bean是如何解决的？）
    循环依赖：在IOC容器创建bean的时候，bean之间相互属性依赖，产生死循环创建问题；
    
 ### Spring的三级缓存？
 public class DefaultSingletonBeanRegistry extends SimpleAliasRegistry implements SingletonBeanRegistry {
     private static final int SUPPRESSED_EXCEPTIONS_LIMIT = 100;
     // 一级缓存 bean池，存放的是 实例
     private final Map<String, Object> singletonObjects = new ConcurrentHashMap(256);
     // 三级缓存 BeanFactory工厂池 存放的是bean的工厂
     private final Map<String, ObjectFactory<?>> singletonFactories = new HashMap(16);
     // 二级缓存 半成品Bean池（未赋值属性的）存放的是 bean代理
     private final Map<String, Object> earlySingletonObjects = new ConcurrentHashMap(16);
     // ....
  }
  
 