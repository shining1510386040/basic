package com.demo.springboot.web.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 查询dao
 * @date 2021/1/26 10:19
 * @see
 */
@Mapper
public interface QueryDao {

    List<Map<String, Object>> listData();
}
