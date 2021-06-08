package com.demo.springboot.dynamicdatasource.service.impl;

import com.demo.springboot.dynamicdatasource.entity.SysUser;
import com.demo.springboot.dynamicdatasource.mapper.SysUserMapper;
import com.demo.springboot.dynamicdatasource.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author Wenyi Cao
 * @since 2021-06-08
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

}
