package com.demo.springboot.consulesweb.service;

import com.demo.springboot.consulesweb.entity.Product;
import com.demo.springboot.consulesweb.entity.jpa.ProductJpa;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 商品服务接口
 * @date 2021/1/29 17:06
 * @see
 */
public interface ProductService {

    ProductJpa getOne(long id);
}
