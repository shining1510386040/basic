# 工程简介
基于Spring-cloud-gateway的API网关；
相关术语：
	Route ： 路由是网关的基本组件。它由ID、目标URI、谓词集合和过滤器集合定义。如果聚合谓词为true，则匹配路由
	Predicate ： This is a Java 8 Function Predicate
	Filter ： 是GatewayFilter的一个实例，在这里，可以在发送下游请求之前或之后修改请求和响应

相关接口：
    路由断言GatewayPredicate接口：这些predicates用于匹配HTTP请求的不同属性
路由断言工厂：RoutePredicateFactory
    具体实现：PathRoutePredicateFactory 按请求路径匹配
              HeaderRoutePredicateFactory 按请求头匹配
              AfterRoutePredicateFactory 匹配某个地区特定时间之后的请求
              BeforeRoutePredicateFactory  特定时间之前
              MethodRoutePredicateFactory 按请求方式（get、post。。）匹配请求
              RemoteAddrRoutePredicateFactory 按远程ip网段范围匹配
    
    过滤器GatewayFilter接口：允许以某种方式修改传入的HTTP请求或传出HTTP响应
网关过滤器工厂：GatewayFilterFactory
    具体实现：AddRequestHeaderGatewayFilterFactory：添加请求头
              AddRequestParameterGatewayFilterFactory：添加请求参数；所有匹配的请求，将给传给下游的请求添加一个查询参数
             AddResponseHeaderGatewayFilterFactory：添加响应头；
            HystrixGatewayFilterFactory：熔断处理；Hystrix网关过滤器允许你将断路器引入网关路由，保护你的服务免受级联失败的影响，并在下游发生故障时提供预备响应。
            PrefixPathGatewayFilterFactory：为匹配的请求添加前缀；所有匹配的请求都将加上前缀/mypath。例如，如果请求是/hello，那么经过这个过滤器后，发出去的请求变成/mypath/hello
            RequestRateLimiterGatewayFilterFactory：请求限流处理；
    请求限流的令牌桶算法
    redis-rate-limiter.replenishRate ： 允许用户每秒处理多少个请求。这是令牌桶被填充的速率。
    redis-rate-limiter.burstCapacity ： 用户在一秒钟内允许执行的最大请求数。这是令牌桶可以容纳的令牌数量。将此值设置为0将阻塞所有请求。
    你想要添加一个过滤器并且把它应用于所有路由的话，你可以用spring.cloud.gateway.default-filters。这个属性接受一个过滤器列表。
    GlobalFilter 全局过滤器接口；
LoadBalancerClientFilter 过时了，负载均衡过滤


# 延伸阅读
参考：https://www.cnblogs.com/cjsblog/p/11099234.html
API网关：系统的唯一入口，客户端或消费端统一经由网关接入微服务；
	网关层实现非业务功能主要包含：
	1.身份验证（验证token）
	2.监控（spring boot admin server端 监控所有的应用）
	3.负载均衡（路由？）
	4.缓存（redis）
	5.请求分片与管理
	6.静态响应处理

	网关应当具备以下功能：

    性能：API高可用，负载均衡，容错机制。
    安全：权限身份认证、脱敏，流量清洗，后端签名（保证全链路可信调用）,黑名单（非法调用的限制）。
    日志：日志记录（spainid,traceid）一旦涉及分布式，全链路跟踪必不可少。
    缓存：数据缓存。
    监控：记录请求响应数据，api耗时分析，性能监控。
    限流：流量控制，错峰流控，可以定义多种限流规则。
    灰度：线上灰度部署，可以减小风险。
    路由：动态路由规则。

