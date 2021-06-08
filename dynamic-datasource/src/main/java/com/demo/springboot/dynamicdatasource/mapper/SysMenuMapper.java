package com.demo.springboot.dynamicdatasource.mapper;

import com.demo.springboot.dynamicdatasource.entity.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 菜单信息表：自关联、树结构、冗余存储、gap级差 Mapper 接口
 * </p>
 *
 * @author Wenyi Cao
 * @since 2021-06-08
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

}
