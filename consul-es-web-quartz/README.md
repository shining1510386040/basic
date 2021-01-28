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
        
        