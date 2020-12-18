package com.demo.springboot.web.mapper;

import com.demo.springboot.web.entity.OrderInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 注解上 写sql 实现mapper映射
 * @date 2020/12/18 16:40
 * @see
 */
@Mapper
public interface OrderInfoMapper {

    @Insert(value = {"insert orderinfo values()", "1"})
    int saveOrder();

    @Delete(value = {"delete from orderinfo where id = ?", "1"})
    int deleteOrder(long id);

    @Update(value = {"update orderinfo set ", "1"})
    int updateOrder();

    @Select(value = {"select * from orderinfo where ", "1"})
    int getOne(long id);

    @Select(value = {"select * from orderinfo where ", "1"})
    List<OrderInfo> getAll(OrderInfo search, int pageNo, int pageSize);


}
