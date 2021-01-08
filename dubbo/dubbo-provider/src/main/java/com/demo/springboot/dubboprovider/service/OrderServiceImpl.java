package com.demo.springboot.dubboprovider.service;

import com.demo.springboot.dubboserviceapi.dto.OrderDto;
import com.demo.springboot.dubboserviceapi.service.OrderService;
import org.apache.dubbo.config.annotation.DubboService;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 订单服务 提供者:
 * @DubboService 暴露服务；
 * @Service 已经过时了
 * @date 2021/1/8 15:00
 * @see
 */
//@Service
@DubboService
public class OrderServiceImpl implements OrderService {

    @Override
    public OrderDto getOrderDetail(String orderId) throws InterruptedException {
        // ....
        // 模拟耗时。。
        TimeUnit.SECONDS.sleep(2);
        return new OrderDto("123", "苹果手机订单", "手机", new BigDecimal(2356), 3);
    }
}
