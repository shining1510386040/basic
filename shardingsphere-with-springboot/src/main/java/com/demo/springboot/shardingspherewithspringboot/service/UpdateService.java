package com.demo.springboot.shardingspherewithspringboot.service;

import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 数据更新服务
 * @date 2021/11/3 17:07
 * @see
 */
@Service
public class UpdateService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 测试事务；
     * // 支持TransactionType.LOCAL, TransactionType.XA, TransactionType.BASE
     * @date 2021/11/3 17:09
     */
    @Transactional(rollbackFor = Exception.class)
    @ShardingTransactionType(TransactionType.XA)
    public void insert() {

        String sql = "INSERT INTO t_order (order_id,user_id,order_name,order_desc,bsflag) VALUES (?,?,?,?,?)";

        Object ret = jdbcTemplate.execute(sql,
                (PreparedStatementCallback<Object>) ps -> {
                    ps.setObject(1, 123);
                    ps.setObject(2, 123);
                    ps.setObject(3, "订单123");
                    ps.setObject(4, "订单123");
                    ps.setObject(5, 0);
                    return ps.executeUpdate();
                }
        );

//        // 测试回滚
//        int error = 1 / 0;
        Object ret2 = jdbcTemplate.execute(sql,
                (PreparedStatementCallback<Object>) ps -> {
                    ps.setObject(1, 123);
                    ps.setObject(2, 122);
                    ps.setObject(3, "订单123");
                    ps.setObject(4, "订单123");
                    ps.setObject(5, 0);
                    return ps.executeUpdate();
                }
        );

    }
}
