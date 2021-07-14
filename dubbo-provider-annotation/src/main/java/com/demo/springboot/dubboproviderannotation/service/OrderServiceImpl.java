package com.demo.springboot.dubboproviderannotation.service;

import com.demo.springboot.dubboserviceapi.exception.CustomDubboException;
import com.demo.springboot.dubboserviceapi.service.OrderService;
import com.demo.springboot.dubboserviceapi.vo.OrderDto;
import com.demo.springboot.dubboserviceapi.vo.ServiceResult;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.Date;
import java.util.Map;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 订单服务实现
 * @date 2021/7/1 17:03
 * @see
 */
@DubboService
public class OrderServiceImpl implements OrderService {
    @Override
    public ServiceResult pageOrder(int pageNo, int pageSize, Map<String, Object> condition) throws CustomDubboException {
        // todo ..
        return null;
    }

    @Override
    public ServiceResult saveOrder(OrderDto orderDto) throws CustomDubboException {
        // todo 。。
        return null;
    }

    @Override
    public OrderDto detailOrder(String orderId) throws CustomDubboException {
        OrderDto build = OrderDto.builder()
                .createBy("admin")
                .createDate(new Date())
                .lastmodifyBy("admin")
                .lastmodifyDate(new Date())
                .orderDesc("我是苹果手机订单-122")
                .orderId("121232")
                .orderName("order1212")
                .totalPrice(2344.00)
                .build();

        return build;
    }

    @Override
    public ServiceResult deleteOrder(String orderId) throws CustomDubboException {
        // todo
        return null;
    }
}
