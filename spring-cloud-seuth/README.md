# 工程简介
    spring-cloud-seuth：基于日志的全链路追踪；
    参考：https://www.cnblogs.com/lishiqi-blog/p/11898841.html
    
    包含了eureka-server工程，作为服务注册中心，eureka-server的创建过程这里不重复；（使用nacos 代替 eureka）
    zipkin-service作为链路追踪服务中心，负责存储链路数据，
    gateway-service作为服务网关工程，负责请求的转发,同时它也作为链路追踪客户端，负责产生数据，并上传给zipkin-service；
    user-service为一个应用服务，对外暴露API接口，同时它也作为链路追踪客户端，负责产生数据。（本模块相当于一个应用服务）

# 延伸阅读

Span:工作的基本单位。例如，发送RPC是一个新的跨度，就像发送响应到RPC一样。Span是由一个唯一的64位ID来标识的，而另一个64位ID用于跟踪。span还具有其他数据，如描述、时间戳事件、键值标注(标记)、导致它们的span的ID和进程ID(通常是IP地址)。

可以启动和停止跨度，并跟踪其时间信息。 创建跨度后，必须在将来的某个时刻停止它。

启动跟踪的初始范围称为根跨度。 该范围的ID值等于跟踪ID。

Trace：一组span形成树状结构。 例如，如果运行分布式大数据存储，则可能由PUT请求形成跟踪。

注解：用于及时记录事件的存在。 使用Brave工具，我们不再需要为Zipkin设置特殊事件，以了解客户端和服务器是谁，请求开始的位置以及结束位置。

cs：客户已发送。 客户提出了请求。 此注释表示跨度的开始。
sr：Server Received：服务器端获得请求并开始处理它。 从此时间戳中减去cs时间戳会显示网络延迟。
ss：服务器已发送。 在完成请求处理时（当响应被发送回客户端时）注释。 从此时间戳中减去sr时间戳会显示服务器端处理请求所需的时间。
cr：客户收到了。 表示跨度的结束。 客户端已成功收到服务器端的响应。 从此时间戳中减去cs时间戳会显示客户端从服务器接收响应所需的全部时间

===========================>>>>
zipkin 的span 是如何存储到db 的？

zipkin 的span 是如何发送到rabbitmq的？
在每个zipkin-客户端配置 将默认的http方式改为rabbit到mq
参考：https://blog.csdn.net/qq_41347385/article/details/106681513

# 发送span 到rabbitMQ？
 默认发送到的队列时zipkin
 需在rabbitMQ上创建zipkin 队列 并采用 direct交换器绑定；
 （queue的routing-key 和 binding的binding-key 一致时 会发送到指定的队列--直连模式）
 
