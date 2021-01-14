package com.demo.springboot.dbconnector.controller;

import com.alibaba.fastjson.JSON;
import com.demo.springboot.dbconnector.core.datasource.DynamicDBUtil;
import com.demo.springboot.dbconnector.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 多数据源测试controller
 * @date 2021/1/14 16:53
 * @see
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private CustomerMapper customerMapper;

    @GetMapping("/getOrders")
    public String get() {
        DynamicDBUtil.setDBSchema("seata_order");
        List<Map<String, Object>> all = customerMapper.getOrders();

        return JSON.toJSONString(all);
    }

    @GetMapping("/getAccounts")
    public String getALL() {
//        DynamicDBUtil.setDBSchema("seata_account");
        DynamicDBUtil.setOrg("323");
        List<Map<String, Object>> all = customerMapper.getAccounts();

        return JSON.toJSONString(all);
    }


}
