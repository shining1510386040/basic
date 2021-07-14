package com.demo.springboot.dubboconsumer.controller;

import com.demo.springboot.dubboserviceapi.service.OrderService;
import com.demo.springboot.dubboserviceapi.vo.OrderDto;
import com.demo.springboot.dubboserviceapi.vo.ServiceResult;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 订单controller
 * @date 2021/7/1 17:28
 * @see
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @DubboReference
    private OrderService orderService;

    @PostMapping("/detail")
    public ServiceResult detailOrder(@RequestParam String orderId) {
        OrderDto orderDto = orderService.detailOrder(orderId);
        ServiceResult ret = new ServiceResult("200", "查询成功");
        ret.setData(orderDto);
        return ret;
    }
}
