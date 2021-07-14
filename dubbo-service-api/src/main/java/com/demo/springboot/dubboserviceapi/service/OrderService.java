package com.demo.springboot.dubboserviceapi.service;

import com.demo.springboot.dubboserviceapi.exception.CustomDubboException;
import com.demo.springboot.dubboserviceapi.vo.OrderDto;
import com.demo.springboot.dubboserviceapi.vo.ServiceResult;

import java.util.Map;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 订单接口
 * @date 2021/7/1 16:43
 * @see
 */
public interface OrderService {

    ServiceResult pageOrder(int pageNo, int pageSize, Map<String, Object> condition) throws CustomDubboException;

    ServiceResult saveOrder(OrderDto orderDto) throws CustomDubboException;

    OrderDto detailOrder(String orderId) throws CustomDubboException;

    ServiceResult deleteOrder(String orderId) throws CustomDubboException;
}
