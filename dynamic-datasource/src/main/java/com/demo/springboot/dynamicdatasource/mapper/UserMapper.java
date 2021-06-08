package com.demo.springboot.dynamicdatasource.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.springboot.dynamicdatasource.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 用户基本操作crud等
 * DS:指定数据源
 * @date 2021/6/8 10:51
 * @see
 */
@DS("master")
public interface UserMapper extends BaseMapper<User> {


    IPage<User> selectPageVo(Page<?> page, @Param("state") Integer state);

}
