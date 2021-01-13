package com.demo.springboot.web.service;

import com.demo.springboot.web.vo.ServiceResult;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 幂等性校验token接口
 * @date 2021/1/12 17:11
 * @see
 */
public interface TokenService {

    /**
     * @param apiToken 前端参数token标识
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 对需幂等性校验的请求生成token并存储在redis中
     * @date 2021/1/12 17:13
     */
    ServiceResult createToken(String apiToken);

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 对需幂等性校验的请求，校验token合法性
     * @date 2021/1/12 17:14
     */
    void checkToken(HttpServletRequest request);

}
