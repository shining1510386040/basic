package com.demo.springboot.dynamicdatasource.mapper;

import com.demo.springboot.dynamicdatasource.entity.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 角色信息表：数据冗余存储，自关联，树结构 Mapper 接口
 * </p>
 *
 * @author Wenyi Cao
 * @since 2021-06-08
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

}
