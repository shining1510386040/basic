package com.demo.springboot.dynamicdatasource.mapper;

import com.demo.springboot.dynamicdatasource.entity.TblOrder;
import com.demo.springboot.dynamicdatasource.entity.TblOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TblOrderMapper {
    long countByExample(TblOrderExample example);

    int deleteByExample(TblOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TblOrder record);

    int insertSelective(TblOrder record);

    List<TblOrder> selectByExample(TblOrderExample example);

    TblOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TblOrder record, @Param("example") TblOrderExample example);

    int updateByExample(@Param("record") TblOrder record, @Param("example") TblOrderExample example);

    int updateByPrimaryKeySelective(TblOrder record);

    int updateByPrimaryKey(TblOrder record);
}