1.配置多个routes 路由时，当有多个匹配时只会匹配最上面一个，所以要制定好匹配规则


测试：
http://localhost:9000/echarts/histogram/index
--》》转发到：http://localhost:8080/echarts/histogram/index

1.基本配置：GatewayAutoConfiguration##GatewayProperties
    路由：routes
    filters过滤器链：
    路由断言：predicates

2.负载均衡配置：GatewayDiscoveryClientAutoConfiguration##DiscoveryLocatorProperties
    使用nacos作为注册中心：
    NacosDiscoveryAutoConfiguration##NacosDiscoveryProperties