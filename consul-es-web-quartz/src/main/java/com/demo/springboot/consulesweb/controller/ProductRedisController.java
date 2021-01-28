package com.demo.springboot.consulesweb.controller;

import com.demo.springboot.consulesweb.entity.mongo.Order;
import com.demo.springboot.consulesweb.entity.redis.ProductRedis;
import com.demo.springboot.consulesweb.repository.mongo.OrderRepository;
import com.demo.springboot.consulesweb.repository.redis.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description redis仓储测试controller
 * @date 2021/1/26 19:22
 * @see
 */
@RestController
@RequestMapping("/redis/product")
public class ProductRedisController {

    @Autowired
    private ProductRepository productRepository;

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 查询单个
     * @date 2021/1/26 19:27
     */
    @GetMapping("/getOne/{id}")
    public Object getOne(@PathVariable long id) {
        Optional<ProductRedis> ret = productRepository.findById(id);
        ProductRedis productRedis = ret.get();
        return productRedis;
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 保存
     * @date 2021/1/26 19:26
     */
    @PostMapping("/save")
    public Object save(ProductRedis data) {
        ProductRedis save = productRepository.save(data);
        return save;
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 查询所有
     * @date 2021/1/26 19:28
     */
    @PostMapping("/list")
    public List<ProductRedis> findAll() {

        Iterable<ProductRedis> all = productRepository.findAll();
        Iterator<ProductRedis> iterator = all.iterator();
        List<ProductRedis> ret = new ArrayList<>(16);
        while (iterator.hasNext()) {
            ret.add(iterator.next());
        }
        return ret;
    }

    @PostMapping("/delete/{id}")
    public void delete(@PathVariable long id) {
        productRepository.deleteById(id);
    }
}
