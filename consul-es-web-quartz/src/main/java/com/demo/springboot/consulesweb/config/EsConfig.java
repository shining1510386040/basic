package com.demo.springboot.consulesweb.config;

import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 非响应式编程es配置
 * @date 2021/1/26 14:34
 * @see
 */
@Configuration
public class EsConfig {

    @Bean
    public RestHighLevelClient restHighLevelClient(RestClientBuilder builder) {
        RestHighLevelClient ret = new RestHighLevelClient(builder);
        return ret;
    }
}
