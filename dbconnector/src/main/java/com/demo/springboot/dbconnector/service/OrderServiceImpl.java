package com.demo.springboot.dbconnector.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 订单服务
 * @date 2021/2/19 19:31
 * @see
 */
@Service
public class OrderServiceImpl {

    /**
     * @author Wenyi Cao
     * @version 1.0
     * @description order db 连接
     * @date 2021/2/19 20:25
     * @param
     * @return
     */
    @Autowired
    private JdbcTemplate jdbcTemplate1;

    /**
     * @author Wenyi Cao
     * @version 1.0
     * @description pay db 连接
     * @date 2021/2/19 20:25
     * @param
     * @return
     */
    @Autowired
    private JdbcTemplate jdbcTemplate2;


    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description Transactional开启事务，遇到异常回滚
     * @date 2021/2/19 19:56
     */
    @Transactional(rollbackFor = Exception.class)
    public String createOrder() {

        //第一个数据采用随机数，保证第二次能执行
        String sql1 = "insert into `order`(id,user_id,product_id,count,money,status) values(3,2,2347,23,100,0)";
        String sql2 = "insert into account(id,user_id,total,used,residue,frozen) values(2,2,2345,0,100,0) ";
        jdbcTemplate1.execute(sql1);
        //第二条插入语句第二次执行时将报错，回滚
        int i = 1 / 0;
        jdbcTemplate2.execute(sql2);
        return "test ok...";
    }
}
