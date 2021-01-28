//package com.demo.springboot.consulesweb.config;
//
//import org.elasticsearch.client.RestHighLevelClient;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.elasticsearch.client.ClientConfiguration;
//import org.springframework.data.elasticsearch.client.RestClients;
//import org.springframework.data.elasticsearch.client.reactive.DefaultReactiveElasticsearchClient;
//import org.springframework.data.elasticsearch.client.reactive.ReactiveElasticsearchClient;
//import org.springframework.data.elasticsearch.core.ReactiveElasticsearchTemplate;
//
//import java.time.Duration;
//
///**
// * @author Wenyi Cao
// * @version 1.0
// * @link
// * @description es配置
// * @date 2021/1/26 14:33
// * @see
// */
//@Configuration
//public class ReactiveEsConfig {
//
//    @Value("${aliyun.es.url}")
//    private String esHostAndPort;
//    @Value("${aliyun.es.username}")
//    private String username;
//    @Value("${aliyun.es.password}")
//    private String password;
//
//    /**
//     * @param
//     * @return
//     * @author Wenyi Cao
//     * @version 1.0
//     * @description 响应式es template
//     * @date 2021/1/28 15:42
//     */
//    @Bean
//    public ReactiveElasticsearchTemplate reactiveElasticsearchTemplate() {
//        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
//                // 1.连接url
//                .connectedTo(esHostAndPort)
//                // 2.超时时间
//                .withConnectTimeout(Duration.ofSeconds(5))
//                //.withSocketTimeout(Duration.ofSeconds(3))
//                //.useSsl()
//                //.withDefaultHeaders(defaultHeaders)
//                // 3.安全验证
////                .withBasicAuth(username, password)
//                // ... other options
//                .build();
//        ReactiveElasticsearchClient client = DefaultReactiveElasticsearchClient.create(clientConfiguration);
//        return new ReactiveElasticsearchTemplate(client);
//    }
//
//    /**
//     * @param
//     * @return
//     * @author Wenyi Cao
//     * @version 1.0
//     * @description spring.data.elasticsearch.cluster-nodes、cluster-name过时
//     * 单例Bean：RestHighLevelClient es java 高级客户端；
//     * @date 2021/1/28 15:20
//     */
//    @Bean
//    RestHighLevelClient elasticsearchClient() {
//        // es 客户端配置
//        ClientConfiguration configuration = ClientConfiguration.builder()
//                // 1.连接url
//                .connectedTo(esHostAndPort)
//                // 2.超时时间
//                .withConnectTimeout(Duration.ofSeconds(5))
//                //.withSocketTimeout(Duration.ofSeconds(3))
//                //.useSsl()
//                //.withDefaultHeaders(defaultHeaders)
//                // 3.安全验证
////                .withBasicAuth(username, password)
//                // ... other options
//                .build();
//        RestHighLevelClient client = RestClients.create(configuration).rest();
//        return client;
//    }
//}
