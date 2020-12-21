package com.demo.springboot.web.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.demo.springboot.web.intercept.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description spring webmvc 配置类
 * @date 2020/12/18 20:05
 * @see
 */
@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        添加拦截器，定义拦截url规则，并定义在拦截器链中的顺序；
//        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/toLogin", "/", "index.html").order(-1);
    }


    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 添加消息转换器：请求报文在SpringMVC中，可以使用@RequestBody和@ResponseBody两个注解，
        // 分别完成请求报文到对象和对象到响应报文的转换，
        // 底层这种灵活的消息转换机制就是利用HttpMessageConverter来实现的

        // 添加第三方  消息转换器完成 空白处理

        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        // 转化特性：
//        1、WriteNullListAsEmpty  ：List字段如果为null,输出为[],而非null
//　　2、WriteNullStringAsEmpty ： 字符类型字段如果为null,输出为"",而非null
//　　3、DisableCircularReferenceDetect ：消除对同一对象循环引用的问题，默认为false（如果不配置有可能会进入死循环）
//　　4、WriteNullBooleanAsFalse：Boolean字段如果为null,输出为false,而非null
//　　5、WriteMapNullValue：是否输出值为null的字段,默认为false。
        converter.setFeatures(SerializerFeature.WriteNullListAsEmpty,//
                SerializerFeature.WriteNullStringAsEmpty,//
                SerializerFeature.DisableCircularReferenceDetect,//
                SerializerFeature.WriteNullBooleanAsFalse,//
                SerializerFeature.WriteMapNullValue//
                );
        converters.add(converter);

    }



}
