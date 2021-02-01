package com.demo.springboot.springsession.config.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 自定义过滤器实现方式1
 * @date 2021/2/1 17:12
 * @see
 */
public class MyFilter1 implements Filter {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 过滤器 过滤逻辑
     * @date 2021/2/1 17:21
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("============>> myfilter begin..");

        try {
            // ...some 过滤处理逻辑
            logger.info("=========>>业务方法执行。。");
            // filter链 继续执行
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {
            logger.error("error!", e);
        }

        logger.info("=========>> myfilter end");
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 初始化操作
     * @date 2021/2/1 17:16
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        logger.info(filterConfig.getFilterName() + "init====>初始化成功");
    }

    @Override
    public void destroy() {

    }
}
