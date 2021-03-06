package com.demo.springboot.sentinel.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.stereotype.Service;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description hello sevice 测试
 * @date 2021/2/2 16:08
 * @see
 */
@Service
public class HelloService {


    /**
     * 限流降级
     *
     * @return
     */
    @SentinelResource(value = "sayHello", blockHandler = "sayHelloExceptionHandler")
    public String sayHello(String name) {
        return "hello," + name;
    }

    /**
     * 熔断降级
     *
     * @return
     */
    @SentinelResource(value = "circuitBreaker", fallback = "circuitBreakerFallback", blockHandler = "sayHelloExceptionHandler")
    public String circuitBreaker(String name) {
        if ("zhangsan".equals(name)) {
            return "hello," + name;
        }
        throw new RuntimeException("发生异常");
    }


    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 熔断处理
     * @date 2021/2/3 15:08
     */
    public String circuitBreakerFallback(String name) {
        return "服务异常，熔断降级, 请稍后重试!";
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 限流处理
     * @date 2021/2/3 15:08
     */
    public String sayHelloExceptionHandler(String name, BlockException ex) {
        return "访问过快，限流降级, 请稍后重试!";
    }
}
