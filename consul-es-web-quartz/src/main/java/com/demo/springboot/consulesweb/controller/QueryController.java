package com.demo.springboot.consulesweb.controller;

import com.demo.springboot.consulesweb.dao.ProductDao;
import com.demo.springboot.consulesweb.dao.QueryDao;
import com.demo.springboot.consulesweb.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @param
 * @author Wenyi Cao
 * @version 1.0
 * @description 查询controller
 * @date 2021/2/23 11:30
 * @return
 */
@RestController
@RequestMapping("/query")
public class QueryController {

    @Autowired
    private QueryDao queryDao;

    @GetMapping("/test")
    public Object getOne() {
        return queryDao.listData();
    }
}
