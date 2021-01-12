package com.demo.springboot.web.service;

import com.demo.springboot.web.exception.ServiceException;
import com.demo.springboot.web.utils.EnumUtils;
import com.demo.springboot.web.utils.StringUtils;
import com.demo.springboot.web.vo.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description token服务实现类
 * @date 2021/1/12 17:15
 * @see
 */
@Service
public class TokenServiceImpl implements TokenService {

    /**
     * 请求参数 token的name
     */
    private final String TOKEN_NAME = "apiToken";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public ServiceResult createToken() {
        //1.生成token
        String token = StringUtils.generateToken(EnumUtils.TokenEnum.API_DEMPOTENT.getPrefix(), new Date());
        //2.存储在redis
        redisTemplate.opsForValue().set(token, token, EnumUtils.TOKEN_EXPIRE_TIME, TimeUnit.SECONDS);
        System.out.println("===================>>token:" + token);
        ServiceResult ret = new ServiceResult("200", "创建token成功");
        ret.setData(token);
        return ret;
    }

    @Override
    public void checkToken(HttpServletRequest request) {
        String token = request.getHeader(TOKEN_NAME);
        if (org.springframework.util.StringUtils.isEmpty(token)) {
            // header中不存在token
            token = request.getParameter(TOKEN_NAME);
            if (org.springframework.util.StringUtils.isEmpty(token)) {
                // 请求体中也不存在token
                throw new ServiceException(EnumUtils.ResponseEnum
                        .ILLEGAL_ARGUMENT.getMessage());

            }
        }
        // todo 有问题。。。
        if (!redisTemplate.hasKey(token)) {
            // redis 中不存在token
            throw new ServiceException(EnumUtils.ResponseEnum.REPEATED_OPERATION.getMessage());
        }

        Boolean delete = redisTemplate.delete(token);
        if (!delete) {
            // 删除不成功
            throw new ServiceException(EnumUtils.ResponseEnum.REPEATED_OPERATION.getMessage());
        }


    }
}
