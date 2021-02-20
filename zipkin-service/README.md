# 工程简介

zipkin-server：zipkin 全链路追踪，server管理端;
访问：http://localhost:9411/zipkin/

可以查看：
1.服务的依赖关系
2.日志

测试：
1.启动zipkin-service（日志收集服务端）
2.启动api-gateway（api网关）
3.启动spring-cloud-seuth（具体业务应用服务）

4.访问接口http://localhost:9000/spring-cloud-seuth-service/app/test
    api-gateway  --> spring-cloud-seuth
   上http://localhost:9411/zipkin/ 查看日志和服务调用依赖情况
   
# 延伸阅读

zipkin :cs模式：
zipkin-server 端：可以java -jar的方式运行；
zipkin-client端：生产追踪日志的一端