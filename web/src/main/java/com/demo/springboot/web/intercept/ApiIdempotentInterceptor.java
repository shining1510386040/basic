package com.demo.springboot.web.intercept;

import com.alibaba.fastjson.JSON;
import com.demo.springboot.web.annotation.ApiIdempotent;
import com.demo.springboot.web.exception.ServiceException;
import com.demo.springboot.web.service.TokenService;
import com.demo.springboot.web.vo.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 幂等性校验拦截器
 * @date 2021/1/12 17:06
 * @see
 */
@Component
public class ApiIdempotentInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 1.判断handler controller方法是否包含幂等性注解，存在校验，不存在放行
        if (!(handler instanceof HandlerMethod)) {
            // 不是controller 方法；放行
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        ApiIdempotent apiIdempotent = method.getAnnotation(ApiIdempotent.class);
        if (apiIdempotent != null) {
            // 标记了 幂等性注解，进行校验
            // 幂等性校验, 校验通过则放行, 校验失败则抛出异常, 并通过统一异常处理返回友好提示
            try {
                tokenService.checkToken(request);
            } catch (ServiceException e) {
                resonseOut(response, new ServiceResult("500", e.getMessage()));
                return false;
            }
        }
        // 否则放行
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    /**
     * 回写给客户端
     *
     * @param response
     * @param result
     * @throws IOException
     */
    private void resonseOut(HttpServletResponse response, ServiceResult result) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        String json = JSON.toJSONString(result);
        out = response.getWriter();
        out.append(json);
    }
}
