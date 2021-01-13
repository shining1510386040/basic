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
    public ServiceResult createToken(String apiToken) {
        //1.生成token
        String token = StringUtils.generateToken(EnumUtils.TokenEnum.API_DEMPOTENT.getPrefix(), new Date());
        //2.存储在redis；key为前端传参，value生成的，意义不大，并不会准确检验值是否正确
        redisTemplate.opsForValue().set(apiToken, token, EnumUtils.TOKEN_EXPIRE_TIME, TimeUnit.SECONDS);
        System.out.println("===================>>token:" + token);
        ServiceResult ret = new ServiceResult("200", "创建token成功");
        ret.setData(token);
        return ret;
    }

    /**
     * @author Wenyi Cao
     * @version 1.0
     * @description 校验token逻辑：相同token的请求只能请求一次，不同token的放行
     *
     * @date 2021/1/13 10:37
     * @param
     * @return
     */
    @Override
    public void checkToken(HttpServletRequest request) {
        // 请求参数 apiToken的值
        String token = request.getHeader(TOKEN_NAME);
        if (org.springframework.util.StringUtils.isEmpty(token)) {
            // header中不存在apiToken 参数
            token = request.getParameter(TOKEN_NAME);
            if (org.springframework.util.StringUtils.isEmpty(token)) {
                // 请求体中也不存在apiToken 参数
                throw new ServiceException(EnumUtils.ResponseEnum
                        .ILLEGAL_ARGUMENT.getMessage());

            }
        }
        // redis 中存在token的key 并且未过期,判定为重复的请求
        // redis key 采用定期删除+惰性删除的策略，key过期后并不一定会马上删除，lru算法
        if (redisTemplate.hasKey(token) && redisTemplate.getExpire(token) > 0) {
            // 重复请求
            throw new ServiceException(EnumUtils.ResponseEnum.REPEATED_OPERATION.getMessage());
        }

        if (redisTemplate.getExpire(token) <= 0) {
            // 过期了手动删除。。
            Boolean delete = redisTemplate.delete(token);
        }
    }
}
