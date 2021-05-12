package com.demo.springboot.springlucence.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


/**
 * @param
 * @author Wenyi Cao
 * @version 1.0
 * @description resttemplate 配置
 * @date 2020/12/23 14:47
 * @return
 */
@Configuration
public class RestTemplateConfig {


    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory) {

        // 添加转换器
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        messageConverters.add(new FormHttpMessageConverter());
        messageConverters.add(new MappingJackson2HttpMessageConverter());

        RestTemplate restTemplate = new RestTemplate(messageConverters);
        restTemplate.setRequestFactory(factory);
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler());
        return new RestTemplate(factory);
    }

    @Bean
    public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        // 超时时间
        factory.setReadTimeout(15000);
        factory.setConnectTimeout(15000);

        return factory;
    }

}
