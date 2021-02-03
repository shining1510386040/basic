package com.demo.springboot.springcloudopenfeign.feignclient;

import com.demo.springboot.commonsapi.entity.Order;
import com.demo.springboot.commonsapi.service.OrderService;
import com.demo.springboot.commonsapi.vo.ServiceResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description feign client 调用客户端
 * @date 2021/2/3 19:30
 * @see
 */
//@FeignClient("api-service")
@FeignClient(name = "orderServiceFeignClient", url = "localhost:8099")
public interface OrderServiceFeignClient {

    @PostMapping("/order/create")
    ServiceResult create(Order order);
}
