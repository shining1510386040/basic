package com.demo.springboot.consulesweb.service.impl;

import com.demo.springboot.consulesweb.entity.jpa.ProductJpa;
import com.demo.springboot.consulesweb.repository.jpa.ProductJpaRepository;
import com.demo.springboot.consulesweb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 商品服务接口实现类
 * @date 2021/1/29 17:08
 * @see
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductJpaRepository productJpaRepository;

    @Override
    public ProductJpa getOne(long id) {
        return productJpaRepository.getOne(id);
    }
}
