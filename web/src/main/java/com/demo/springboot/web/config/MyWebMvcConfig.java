package com.demo.springboot.web.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.demo.springboot.web.intercept.ApiIdempotentInterceptor;
import com.demo.springboot.web.intercept.LoginInterceptor;
import com.demo.springboot.web.intercept.RequestLimitInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
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

    @Autowired
    private RequestLimitInterceptor requestLimitInterceptor;

    @Autowired
    private ApiIdempotentInterceptor apiIdempotentInterceptor;

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 添加拦截器
     * @date 2020/12/28 10:27
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        添加拦截器，定义拦截url规则，并定义在拦截器链中的顺序；
//        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/toLogin", "/", "index.html").order(-1);
        // 限流拦截器，接口防刷
        registry.addInterceptor(requestLimitInterceptor).addPathPatterns("/**");
        // 幂等性拦截器
        registry.addInterceptor(apiIdempotentInterceptor).addPathPatterns("/**");
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 添加消息转换器
     * @date 2020/12/28 10:27
     */
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

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 跨域设置
     * @date 2020/12/25 17:19
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .maxAge(3600)
                .allowCredentials(true);
    }


    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 同步调用远程服务RestTemplate配置
     * @date 2021/1/12 10:52
     */
    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
        return new RestTemplate(factory);
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description http请求客户端工厂
     * @date 2021/1/12 10:53
     */
    @Bean
    public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        // 超时时间
        factory.setReadTimeout(15000);
        factory.setConnectTimeout(15000);
        return factory;
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description webmvc 静态资源处理（js，css，html）
     * <p>
     * 默认ResourceProperties：classpath:/META-INF/resources/", "classpath:/resources/",
     * "classpath:/static/", "classpath:/public/
     * @date 2020/12/29 15:14
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //配置静态资源处理
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/resources/", "classpath:/static/",
                        "classpath:/public/", "file:///tmp/webapps/");
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }


}
