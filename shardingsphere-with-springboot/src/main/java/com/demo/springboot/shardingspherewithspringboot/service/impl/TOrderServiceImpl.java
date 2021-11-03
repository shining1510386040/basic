package com.demo.springboot.shardingspherewithspringboot.service.impl;

import com.demo.springboot.shardingspherewithspringboot.entity.TOrder;
import com.demo.springboot.shardingspherewithspringboot.mapper.TOrderMapper;
import com.demo.springboot.shardingspherewithspringboot.service.TOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Wenyi Cao
 * @since 2021-11-03
 */
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements TOrderService {

}
