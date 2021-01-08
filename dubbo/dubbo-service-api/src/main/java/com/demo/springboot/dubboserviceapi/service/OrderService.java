package com.demo.springboot.dubboserviceapi.service;

import com.demo.springboot.dubboserviceapi.dto.OrderDto;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 订单接口
 * @date 2021/1/8 14:48
 * @see
 */
public interface OrderService {

    OrderDto getOrderDetail(String orderId) throws InterruptedException;

}
