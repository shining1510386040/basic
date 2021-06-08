package com.demo.springboot.dynamicdatasource.service.impl;

import com.demo.springboot.dynamicdatasource.entity.SysRole;
import com.demo.springboot.dynamicdatasource.mapper.SysRoleMapper;
import com.demo.springboot.dynamicdatasource.service.SysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色信息表：数据冗余存储，自关联，树结构 服务实现类
 * </p>
 *
 * @author Wenyi Cao
 * @since 2021-06-08
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

}
