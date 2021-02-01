# 工程简介
    1.配置中心+服务发现注册中心：consul
    2.搜索：elasticsearch
    3.web：springboot+ssm
    4.定时任务：quartz
    


# 延伸阅读

1. consul：如何持久化？

测试时：
consul agent -dev ：不会持久化数据
生产上：
consul agent -server -bootstrap-expect=1 -data-dir=/tmp/consul -node=agent-one -bind=172.20.20.10 -enable-script-checks=true -config-dir=/etc/consul.d
参数解析

-dev 声明为测试模式，不会持久化数据，即不需要设定data-dir
-server 声明为server节点，一个集群只能有1个server，不能多也不能少，其他的都为client，不带-server tag即可
-config-dir 绑定一些业务服务,该路径下，存在类似helloworld.json服务配置config-dir=/etc/consul.d
-node 节点名称，默认为机器名，可以手动指定 -node=agent-1
-bootstrap-expect server节点还需要等待几个子节点加入，-bootstrap-expect=1
-data-dir 在非测试模式下，需要持久化数据，会存放在该路径,-data-dir=/tmp/consul
-enable-script-checks=true 据说是用来跑脚本，达到健康查询之类的效果,不明0 0

2. bootstrap.yml 配置未生效？
SpringBoot 项目中如果没有依赖 spring-cloud-context 的话，是不会读取bootstrap.properties 文件

bootstrap.yml 优先级高于application.yml

3.Spring Data 项目？
    一个数据访问的技术框架：关系型数据库、非关系型数据库、map-reduce 框架、基于云的数据服务
    主要子模块：
    Spring Data Commons - Core Spring concepts underpinning every Spring Data module.
    公共组件：核心接口；仓储接口：(顶级接口)Repository<T,ID>  --> (增删改查)CrudRepository<T, ID>  -->  （分页+排序）PagingAndSortingRepository<T, ID>
    Spring Data JDBC - Spring Data repository support for JDBC.
    jdbc的支持：
    Spring Data JPA - Spring Data repository support for JPA.
    jpa的支持：
    Spring Data KeyValue - Map based repositories and SPIs to easily build a Spring Data module for key-value stores.
    基于Map的实现KV存储：
    Spring Data LDAP - Spring Data repository support for Spring LDAP.
    LDAP的支持：目录数据库，方便查询的，类似于solar
    Spring Data MongoDB - Spring based, object-document support and repositories for MongoDB.
    mongodb的支持：
    Spring Data Redis - Easy configuration and access to Redis from Spring applications.
    redis的支持：
    Spring Data REST - Exports Spring Data repositories as hypermedia-driven RESTful resources.
    restful接口支持：
    
    Spring Data Elasticsearch - Spring Data module for Elasticsearch.
    elasticsearch的支持：
    Spring Data Neo4j - Spring-based, object-graph support and repositories for Neo4j.
    neo4j图数据库的支持：
    Spring Data for Apache Solr - Easy configuration and access to Apache Solr for your search-oriented Spring applications.
    solr搜索的支持：
    
    核心接口：
    仓储接口：(顶级接口)Repository<T,ID>  --> (增删改查)CrudRepository<T, ID>  -->  （分页+排序）PagingAndSortingRepository<T, ID>
        
3.JPA 仓储接口的自定义接口规范？
    在查询时，通常需要同时根据多个属性进行查询，且查询的条件也格式各样（大于某个值、在某个范围等等），Spring Data JPA 为此提供了一些表达条件查询的关键字，大致如下：
    多属性查询：
    And --- 等价于 SQL 中的 and 关键字，比如 findByUsernameAndPassword(String user, Striang pwd)
    Or --- 等价于 SQL 中的 or 关键字，比如 findByUsernameOrAddress(String user, String addr)
    Between --- 等价于 SQL 中的 between 关键字，比如 findBySalaryBetween(int max, int min)
    LessThan --- 等价于 SQL 中的 "<"，比如 findBySalaryLessThan(int max)
    GreaterThan --- 等价于 SQL 中的">"，比如 findBySalaryGreaterThan(int min)
    IsNull --- 等价于 SQL 中的 "is null"，比如 findByUsernameIsNull()
    IsNotNull --- 等价于 SQL 中的 "is not null"，比如 findByUsernameIsNotNull()
    NotNull --- 与 IsNotNull 等价
    Like --- 等价于 SQL 中的 "like"，比如 findByUsernameLike(String user)
    NotLike --- 等价于 SQL 中的 "not like"，比如 findByUsernameNotLike(String user)
    OrderBy ---等价于 SQL 中的 "order by"，比如 findByUsernameOrderBySalaryAsc(String user)
    Not --- 等价于 SQL 中的 "！ ="，比如 findByUsernameNot(String user)
    In --- 等价于 SQL 中的 "in"，比如 findByUsernameIn(Collection<String> userList) ，方法的参数可以是 Collection 类型，也可以是数组或者不定长参数
    NotIn --- 等价于 SQL 中的 "not in"，比如 findByUsernameNotIn(Collection<String> userList) ，方法的参数可以是 Collection 类型，也可以是数组或者不定长参数
    命名查询：@NamedQuery
    需要满足”DomainClass.methodName()”的 命名规则。
    
4.Spring data Jdbc 数据操作？
    JdbcTemplate 主要提供以下几类方法：

    execute 方法：可以用于执行任何 SQL 语句，一般用于执行 DDL 语句；
    update 方法及 batchUpdate 方法： update 方法用于执行新增、修改、删除等语句； batchUpdate 方法用于执行批处理相关语句；
    query 方法及 queryForXXX 方法：用于执行查询相关语句；
    call 方法：用于执行存储过程、函数相关语句
    
    仓储？
    Spring Data JDBC背后的想法是提供对关系数据库的访问，而无需处理JPA的复杂性。JPA提供延迟加载，缓存和脏跟踪等功能。果你需要这些功能会很很棒，但会让猜测JPA的行为比非JPA更难。
    
    延迟加载可能会在你不需要时触发昂贵的语句，或者它可能会因异常而失败。当你想要比较一个实体的两个版本是哪个变成脏数据时，缓存可能会妨碍你，让你很难找到所有持久性操作都通过的那个点。
    
    Spring Data JDBC目标是实现更简单的模型，不会有缓存，脏数据跟踪或延迟加载。相反，只有在调用数据库方法时才会发出SQL语句。方法返回的对象会完全加载，不会有延迟。实体没有“会话”和代理。
    
    仓储实体要求：一个@Id（org.springframework.data.annotation.Id 不是jpa的）即可
    
5.quartz 定时任务？

spring 分别对Quartz的三个方面，Job & JobDetail,Trigger和Scheduler进行了封装,
Spring Quartz 和 Spring Scheduling是任务调度的两种方案，
两者在使用上完全没有关系，
Spring Scheduling使用的是JDK的类库实现的任务调度,
Spring Quartz对Quartz的封装如下：

    Job -> QuartzJobBean
    JobDetail -> JobDetailFactoryBean + MethodInvokeingJobDetailFactoryBean
    JobFactory -> SpringBeanJobFactory
    Trigger -> CronTriggerFactoryBean & SimpleTriggerFactoryBean
    Scheduler -> SchedulerFactoryBean
    ThreadPool -> LocalTaskExecutorThreadPool 用于使用java.util.concurrent.Executor来实现线程池；
    ClassLoaderHelper -> ResourceLoaderClassLoaderHelper
6.服务注册不到consul注册中心的问题？
    1.consul 和spring boot的版本不兼容导致的
    2.主要是spring boot和spring cloud的版本不兼容导致的；
    最新的版本兼容说明参考网站：https://start.spring.io/actuator/info
    具体的细粒度版本依赖关系：https://blog.csdn.net/qq_47759220/article/details/109351733?ops_request_misc=%25257B%252522request%25255Fid%252522%25253A%252522161192128716780262527445%252522%25252C%252522scm%252522%25253A%25252220140713.130102334..%252522%25257D&request_id=161192128716780262527445&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~blog~sobaiduend~default-3-109351733.pc_v2_rank_blog_default&utm_term=springboot%25E5%2592%258Cspringcloud%25E7%2589%2588%25E6%259C%25AC%25E5%25AF%25B9%25E5%25BA%2594%25E5%2585%25B3%25E7%25B3%25BB
    
 