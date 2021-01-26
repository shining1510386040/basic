package com.demo.springboot.consulesweb.controller;

import com.demo.springboot.consulesweb.dao.ProductDao;
import com.demo.springboot.consulesweb.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 产品controller
 * @date 2021/1/26 10:20
 * @see
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductDao productDao;

    @GetMapping("/getOne")
    public Product getOne(@RequestParam long id) {
        return productDao.getOne(id);
    }
}
