package com.demo.springboot.consulesweb.dao;

import com.demo.springboot.consulesweb.entity.Product;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 产品dao（mapper接口）
 * @date 2021/1/26 10:19
 * @see
 */
@Mapper
public interface ProductDao {

    Product getOne(long id);
}
