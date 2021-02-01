package com.demo.springboot.springsession.config;

import com.demo.springboot.springsession.config.filter.MyFilter1;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.web.http.SessionRepositoryFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description Spring mvc 配置
 * @date 2021/2/1 17:00
 * @see
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {


    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 声明Bean的方式配置过滤器
     * @date 2021/2/1 17:06
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {

        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new MyFilter1());
        filterRegistrationBean.setName("myfilter1");
        List urlPatternList = new ArrayList();
        urlPatternList.add("/*");
        filterRegistrationBean.setUrlPatterns(urlPatternList);
        return filterRegistrationBean;
    }


}
