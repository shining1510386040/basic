package com.demo.springboot.web.mapper;

import com.demo.springboot.web.entity.OrderInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 注解上 写sql 实现mapper映射,不推荐使用，
 * @date 2020/12/18 16:40
 * @see
 */
@Mapper
public interface OrderInfoMapper {

    // 插入数据 不返回主键；手动赋值给主键id值
    @Insert(value = "insert orderinfo(id,orderName,orderDesc,totalPrice) values(#{id},#{orderName},#{orderDesc},#{totalPrice})")
    int saveOrder(OrderInfo data);

    // 插入数据 并返回自增主键mysql；selectKey oracle
    @Insert(value = "insert orderinfo(id,orderName,orderDesc,totalPrice) values(#{id},#{orderName},#{orderDesc},#{totalPrice})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int saveOrderKey(OrderInfo data);

    @Delete(value = "delete from orderinfo where id = #{id}")
    int deleteOrder(@Param("id") long id);

    @Update(value = "update orderinfo set orderName = #{orderName}," +
            "orderDesc=#{orderDesc},totalPrice=#{totalPrice}")
    int updateOrder(OrderInfo data);

    @Select(value = "select * from orderinfo where 1=1 and id = #{id} ")
    OrderInfo getOne(@Param("id") long id);

    @Select(value ="select * from orderinfo where 1=1 and orderName like concat(#{orderName},'%') " +
            "and orderDesc like concat(#{orderDesc},'%')")
    List<OrderInfo> getAll(OrderInfo search);


}
