package com.demo.springboot.dubboconsumer.controller;

import com.demo.springboot.dubboserviceapi.dto.OrderDto;
import com.demo.springboot.dubboserviceapi.service.OrderService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description order controller
 * @date 2021/1/8 16:36
 * @see
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    /**
     * 使用@DubboReference 调用 dubbo服务
     */
    @DubboReference
    private OrderService orderService;

    @GetMapping("/getOrderDetail")
    public OrderDto getOrderDetail(@RequestParam String orderId) {

        OrderDto orderDetail = null;
        try {
            orderDetail = orderService.getOrderDetail(orderId);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return orderDetail;
    }


}
