//package com.demo.springboot.apigateway.config;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
//import org.springframework.cloud.gateway.filter.ratelimit.RateLimiter;
//import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
//import org.springframework.cloud.gateway.route.Route;
//import org.springframework.cloud.gateway.route.RouteLocator;
//import org.springframework.cloud.gateway.route.builder.PredicateSpec;
//import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//import java.util.function.Function;
//
///**
// * @author Wenyi Cao
// * @version 1.0
// * @link
// * @description 网关配置
// * @date 2021/1/21 16:37
// * @see
// */
//@Configuration
//public class ApiGatewayConfig {
//
//    private Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    @Bean
//    public KeyResolver keyResolver() {
//
//        return new KeyResolver() {
//            @Override
//            public Mono<String> resolve(ServerWebExchange exchange) {
//                // 根据请求参数中字段进行限流
//                String value = exchange.getRequest().getPath().contextPath().value();
//                logger.info("=========================>>>>url:{}", value);
//                return Mono.just(exchange.getRequest().getQueryParams().getFirst("api"));
//            }
//        };
//    }
//
//    /**
//     * @param
//     * @return
//     * @author Wenyi Cao
//     * @version 1.0
//     * @description 请求速率限流
//     * @date 2021/1/21 16:59
//     */
//    @Bean
//    public RateLimiter rateLimiter() {
//
//        // 入参：令牌桶速率，最大容量(每秒)
//        return new RedisRateLimiter(1, 15);
//    }
//
//    /**
//     * @param
//     * @return
//     * @author Wenyi Cao
//     * @version 1.0
//     * @description 自定义路由匹配；多个断言谓词
//     * @date 2021/1/21 16:57
//     */
//    @Bean
//    public RouteLocator customerRouterLocator(RouteLocatorBuilder builder) {
//
//        RouteLocator ret = builder.routes()
////                // 1.按路径匹配的路由
////                .route("path_route", new Function<PredicateSpec, Route.AsyncBuilder>() {
////                    @Override
////                    public Route.AsyncBuilder apply(PredicateSpec predicateSpec) {
////                        return predicateSpec.path("/account").uri("http://localhost:8080");
////                    }
////                })
////                // 2.按域名匹配的路由
////                .route("host_route", e -> e.host("*.cloudcc.com").filters(f -> f.hystrix(c -> c.setName("slowcmd"))).uri("http://www.baidu.com"))
////                // 3. 熔断器filter
////                .route("hystrix_fallback_route", r -> r.method("get", "post")
////                        .filters(f -> f.hystrix(c -> c.setName("slowcmd").setFallbackUri("forward:/hystrixfallback")))
////                        .uri("http://loalhost:8080"))
//                // 4. 请求限流filter
////                .route("limit_route", r -> r.method("get", "post")
////                        .filters(f -> f.requestRateLimiter(c -> c.setRateLimiter(rateLimiter()).setKeyResolver(keyResolver())))
////                        .uri("http://loalhost:8080"))
//                .route("method_route",r->r.method("get","post").uri("http://127.0.0.1:8080/"))
//                .build();
//        return ret;
//    }
//}
