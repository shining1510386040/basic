package com.demo.springboot.consulesweb.repository.jdbc;

import com.demo.springboot.consulesweb.entity.jdbc.ProductJdbc;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 产品 jdbc仓储接口
 * @date 2021/1/29 10:05
 * @see
 */
@Repository
public interface ProductJdbcRepository extends PagingAndSortingRepository<ProductJdbc, Long> {

    // 自定义仓储接口
    // todo 有问题：Consider defining a bean of type 'com.demo.springboot.consulesweb.repository.jdbc.ProductJdbcRepository' in your configuration.
    @Query(value = "select * from product_jdbc where 1=1")
    List<ProductJdbc> listProductJdbc();

}
