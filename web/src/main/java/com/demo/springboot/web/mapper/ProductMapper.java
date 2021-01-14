package com.demo.springboot.web.mapper;

import com.demo.springboot.web.entity.OrderInfo;
import com.demo.springboot.web.entity.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @param
 * @author Wenyi Cao
 * @version 1.0
 * @description 商品mapper
 * @date 2021/1/13 18:43
 * @return
 */
@Mapper
public interface ProductMapper {

    void saveProduct(Product product);

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 批量新增
     * @date 2021/1/13 19:40
     */
    int saveProductBatch(List<Product> list);

    /**
     * @param productName 商品名称
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 按商品名称查询
     * @date 2021/1/13 20:26
     */
    List<Product> getProductInfo(String productName);
}
