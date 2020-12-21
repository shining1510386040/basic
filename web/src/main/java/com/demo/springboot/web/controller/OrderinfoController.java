package com.demo.springboot.web.controller;

import com.demo.springboot.web.entity.OrderInfo;
import com.demo.springboot.web.mapper.OrderInfoMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 订单处理controller
 * @date 2020/12/21 15:59
 * @see
 */
@RestController
@RequestMapping("/order")
public class OrderinfoController {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @PostMapping("/save")
    public int saveOrder(OrderInfo data) {
        return orderInfoMapper.saveOrderKey(data);
    }

    @PostMapping("/delete/{id}")
    public int deleteOrder(@PathVariable long id) {
        return orderInfoMapper.deleteOrder(id);
    }

    @PostMapping("/update")
    public int updateOrder(OrderInfo data) {
        return orderInfoMapper.updateOrder(data);
    }

    @GetMapping("/getOne/{id}")
    public OrderInfo getOrder(@PathVariable long id) {
        return orderInfoMapper.getOne(id);
    }

    @PostMapping("/getAll")
    public PageInfo<OrderInfo> getPage(OrderInfo search, @RequestParam int pageNo, @RequestParam int pageSize) {

        PageHelper.startPage(pageNo, pageSize);

        List<OrderInfo> all = orderInfoMapper.getAll(search);
        return new PageInfo<>(all);

    }


}
