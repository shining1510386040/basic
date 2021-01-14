package com.demo.springboot.dbconnector.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description mapper
 * @date 2021/1/14 16:56
 * @see
 */
@Mapper
public interface CustomerMapper {

    List<Map<String, Object>> getOrders();

    List<Map<String, Object>> getAccounts();


}
