package com.demo.springboot.springcloudopenfeign.controller;

import com.demo.springboot.commonsapi.entity.Order;
import com.demo.springboot.commonsapi.vo.ServiceResult;
import com.demo.springboot.springcloudopenfeign.feignclient.OrderServiceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 测试open feign调用
 * @date 2021/2/3 19:38
 * @see
 */
@RestController
public class OrderFeignController {

    @Autowired
    private OrderServiceFeignClient orderServiceFeignClient;

    @PostMapping("/order/create")
    public String test() {
        // todo ..有问题

        // feign.FeignException: status 404 reading OrderServiceFeignClient#create(Order)
        ServiceResult serviceResult = orderServiceFeignClient.create(new Order());

        System.out.println(serviceResult);
        return "ok";
    }
}
