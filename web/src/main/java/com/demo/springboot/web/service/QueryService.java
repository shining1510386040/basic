package com.demo.springboot.web.service;

import com.demo.springboot.web.entity.Account;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 查询服务
 * @date 2020/12/28 12:20
 * @see
 */
@Service
@CacheConfig(cacheNames = {"accounts"})
public class QueryService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /*1. @Cacheable的几个属性详解：
     *       cacheNames/value：指定缓存组件的名字
     *       key：缓存数据使用的key,可以用它来指定。默认使用方法参数的值，一般不需要指定
     *       keyGenerator：作用和key一样，二选一
     *       cacheManager和cacheResolver作用相同：指定缓存管理器，二选一
     *       condition：指定符合条件才缓存，比如：condition="#id>3"
     *                   也就是说传入的参数id>3才缓存数据
     *      unless：否定缓存，当unless为true时不缓存，可以获取方法结果进行判断
     *      sync：是否使用异步模式*/
    //@Cacheable(cacheNames= "person")
    //@Cacheable(cacheNames= "person",key="#id",condition="#id>3")
//    @Cacheable(cacheNames= "person",key="#id")
    // 当查询结果条数大于20 就不会进行缓存
    @Cacheable(value = "accounts")
    public List<Map<String, Object>> getAccountByUserName(String username) {

        logger.error("=======================>>>>进入 查询方法：getAccountByUserName");

        String sql = "select * from account where 1=1 and username like ?";
        Object[] args = new Object[]{username + "%"};
//        BeanPropertyRowMapper propertyRowMapper = new BeanPropertyRowMapper<>(Account.class);
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql, args);

//        Account account = jdbcTemplate.queryForObject(sql, args, Account.class);
//        int update = jdbcTemplate.update(sql, args);

        return mapList;
    }

//    @Cacheable(cacheNames="books", key="#isbn")
//    public Book findBook(ISBN isbn, boolean checkWarehouse, boolean includeUsed)
//
//    @Cacheable(cacheNames="books", key="#isbn.rawNumber")
//    public Book findBook(ISBN isbn, boolean checkWarehouse, boolean includeUsed)
//
//    @Cacheable(cacheNames="books", key="T(someType).hash(#isbn)")
//    public Book findBook(ISBN isbn, boolean checkWarehouse, boolean includeUsed)

//    第一个实例，我们使用三个参数中的其中一个来构建key。
//    第二个实例，我们使用参数内部的field来构建key。
//    第三个实例，我们使用静态方法来生成key。
}
