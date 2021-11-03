package com.demo.springboot.shardingspherewithspringboot.controller;


import com.demo.springboot.shardingspherewithspringboot.common.ServiceResult;
import com.demo.springboot.shardingspherewithspringboot.entity.TOrder;
import com.demo.springboot.shardingspherewithspringboot.service.TOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 测试-t_order 分表
 * </p>
 *
 * @author Wenyi Cao
 * @since 2021-11-03
 */
@RestController
@RequestMapping("/order")
public class TOrderController {

    @Autowired
    private TOrderService orderService;

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 测试保存订单
     * @date 2021/11/3 10:11
     */
    @PostMapping("/save")
    public ServiceResult saveOrder() {

        List<TOrder> orderList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            TOrder cur = TOrder.builder()
                    .orderId(i)
                    .orderName("测试订单" + i)
                    .orderDesc("测试订单")
                    .bsflag(0)
                    .createBy("admin")
                    .createDate(LocalDateTime.now())
                    .lastModifyBy("admin")
                    .lastModifyDate(LocalDateTime.now())
                    .userId(i)
                    .build();
            orderList.add(cur);

        }
        boolean b = orderService.saveBatch(orderList);
        return new ServiceResult("200", "操作成功");
    }

}
