# 工程简介
spring-cloud-openfeign 测试；


# 延伸阅读

1.feign与ribbon和openfeign的概念？
    feign是一个声明式web服务客户端，让编写web客户服务端变得非常简单。
    只需要创建一个接口并在接口添加注解即可。
    Feign的作用：Feign旨在使编写Java Http客户端变得更容易。

    在使用Ribbon + RestTemplate时，利用RestTemplate对http请求的封装处理, 形成了一套模版化的调用方法。
    但是在实际开发中，由于对服务依赖的调用可能不止一处,往往一个接口会被多 处调用，
    所以通常都会针对每个微服务自行封装-些客户端类来包装这些依赖服务的调用。
    所以,** Feign在此基础上做了进一步封装, 由他来帮助我们定义和实现依赖服务接口的定义。
    **在Feign的实现下我们只需创建一个接口并使用注解的方式来配置它
    (以前是Dao接口 上面标注Mapper注解现在是一个微服务接口 上面标注一个Feign注解即可)，
    即可完成对服务提供方的接口绑定，简化了使用Spring cloud Ribbon时,自动封装服务调用客户端的开发量。
Feign集成了Ribbon
利用Ribbon维护了服务列表信息，并且通过轮询实现了客户端的负载均衡。
而与Ribbon不同的是，通过feign只需要定义服务绑定接口且以声明式的方法，优雅而简单的实现了服务调用

2.openfeign?
OpenFeign的README是Feign makes writing java http clients easier，让写java http clients更容易。所以不仅是Spring cloud生态用到，只要用到Http Client的地方都可以用它

3.feign.FeignException: status 404 reading OrderServiceFeignClient#create(Order)?
 
 问题原因:http 请求的路径 必须相同( 提供方 和 open feign 调用方)
 