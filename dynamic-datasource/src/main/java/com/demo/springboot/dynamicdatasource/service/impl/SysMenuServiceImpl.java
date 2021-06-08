package com.demo.springboot.dynamicdatasource.service.impl;

import com.demo.springboot.dynamicdatasource.entity.SysMenu;
import com.demo.springboot.dynamicdatasource.mapper.SysMenuMapper;
import com.demo.springboot.dynamicdatasource.service.SysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜单信息表：自关联、树结构、冗余存储、gap级差 服务实现类
 * </p>
 *
 * @author Wenyi Cao
 * @since 2021-06-08
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

}
