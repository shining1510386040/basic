package com.demo.springboot.shardingspherewithspringboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.springboot.shardingspherewithspringboot.common.ServiceResult;
import com.demo.springboot.shardingspherewithspringboot.entity.TOrder;
import com.demo.springboot.shardingspherewithspringboot.service.TOrderService;
import com.demo.springboot.shardingspherewithspringboot.service.UpdateService;
import com.demo.springboot.shardingspherewithspringboot.vo.CommReqParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private UpdateService updateService;

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

        // 分库分表策略：取模算法
//        先按userId 分库 再按orderId 分表 2*2 = 4 个分片
        List<TOrder> orderList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            TOrder cur = TOrder.builder()
                    .orderId(i + 1)
                    .orderName("测试订单" + i)
                    .orderDesc("测试订单")
                    .bsflag(0)
                    .createBy("admin")
                    .createDate(new Date())
                    .lastModifyBy("admin")
                    .lastModifyDate(new Date())
                    .userId(i)
                    .build();
            orderList.add(cur);

        }
        boolean b = orderService.saveBatch(orderList);
        return new ServiceResult("200", "操作成功");
    }


    @PostMapping("/findAll")
    public ServiceResult findAll() {
        List<TOrder> list = orderService.list();
        ServiceResult ret = new ServiceResult("200", "操作成功");
        ret.setData(list);
        return ret;
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 测试统计总数
     * @date 2021/11/3 15:49
     */
    @PostMapping("/countNum")
    public ServiceResult countNum(@RequestBody CommReqParams commReqParams) {
        Map<String, Object> body = commReqParams.getBody();
        Map<String, Object> condition = (Map<String, Object>) body.get("condition");
        Object userId = condition.get("userId");
        userId = Integer.valueOf(userId + "");
        QueryWrapper<TOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        int count = orderService.count(queryWrapper);
        ServiceResult ret = new ServiceResult("200", "操作成功");
        ret.setData(count);
        return ret;
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 分页查询
     * @date 2021/11/3 15:56
     */
    @PostMapping("/pageOrder")
    public ServiceResult pageOrder(@RequestBody CommReqParams commReqParams) {

        Map<String, Object> body = commReqParams.getBody();
        Integer pageNo = (Integer) body.get("pageNo");
        Integer pageSize = (Integer) body.get("pageSize");

        Map<String, Object> condition = (Map<String, Object>) body.get("condition");

        QueryWrapper<TOrder> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("user_id", condition.get("userId"));
        queryWrapper.like("order_name", condition.get("orderName"));
        Page<TOrder> page = new Page<>(pageNo, pageSize);
        Page<TOrder> data = orderService.page(page, queryWrapper);
        ServiceResult ret = new ServiceResult("200", "查询成功");
        ret.setData(data);

        return ret;
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 测试事务
     * @date 2021/11/3 17:35
     */
    @GetMapping("/tx")
    public ServiceResult testTx() {

        updateService.insert();
        return new ServiceResult("200", "操作成功");
    }
}
