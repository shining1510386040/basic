package com.demo.springboot.shardingspherewithspringboot.common;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @param
 * @author Wenyi Cao
 * @version 1.0
 * @description 日志拦截-处理
 * @date 2021/9/6 14:51
 * @return
 */
@Component
public class LogInterceptor extends HandlerInterceptorAdapter {
    /**
     * 请求ID
     */
    public static final String REQUEST_ID = "REQUEST_ID";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestId = UUID.randomUUID().toString();
        if (StringUtils.isEmpty(MDC.get(REQUEST_ID))) {
            MDC.put(REQUEST_ID, requestId);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        MDC.remove(REQUEST_ID);
    }
}
