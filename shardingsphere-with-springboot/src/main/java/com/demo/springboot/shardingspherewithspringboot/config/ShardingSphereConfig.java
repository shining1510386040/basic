//package com.demo.springboot.shardingspherewithspringboot.config;
//
//import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//
///**
// * @author Wenyi Cao
// * @version 1.0
// * @link
// * @description sharding-jdbc 配置
// * @date 2021/11/2 20:10
// * @see
// */
//@Configuration
//@EnableTransactionManagement
//public class ShardingSphereConfig {
//
//    /**
//     * @param
//     * @return
//     * @author Wenyi Cao
//     * @version 1.0
//     * @description 事务管理器
//     * @date 2021/11/3 17:06
//     */
//    @Bean
//    public PlatformTransactionManager txManager(final DataSource dataSource) {
//
//        return new DataSourceTransactionManager(dataSource);
//    }
//
//    @Bean
//    public JdbcTemplate jdbcTemplate(final DataSource dataSource) {
//        return new JdbcTemplate(dataSource);
//    }
//
//}
