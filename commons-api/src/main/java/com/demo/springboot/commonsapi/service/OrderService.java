package com.demo.springboot.commonsapi.service;

import com.demo.springboot.commonsapi.entity.Order;
import com.demo.springboot.commonsapi.vo.ServiceResult;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 订单接口(http 的接口)
 * 注:其实现类为xxxController
 * @date 2021/2/3 19:09
 * @see
 */
public interface OrderService {

    @PostMapping("/order/create")
    ServiceResult create(Order order);
}
