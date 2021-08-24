//package com.demo.springboot.springbasic.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import javax.sql.DataSource;
//
///**
// * @author Wenyi Cao
// * @version 1.0
// * @link
// * @description jdbc配置- 可使用springboot的自动化配置 JdbcAutoConfiguration
// * @date 2021/8/24 14:38
// * @see
// */
//@Configuration
//public class JdbcConfig {
//
//    @Bean
//    public JdbcTemplate rechargeJdbcTemplate(DataSource rechargeDataSource, @Value("${hikaricp.fetchSize}") Integer fetchSize) {
//        JdbcTemplate jdbcTemplate = new JdbcTemplate(rechargeDataSource, true);
//        jdbcTemplate.setLazyInit(true);
//        // 必须设置 autoCommit = false，否则 该设置不起作用
//        // 单次tcp数据传输返回的条数，oracel 默认是10，可以设置为 100等
//        jdbcTemplate.setFetchSize(fetchSize);
//        // 最多返回的行数，多了会出现JVM的 OOM
//        jdbcTemplate.setMaxRows(50000);
////        该方法会 在 事务识别加上 超时设置，超时的事务将会被终止, 作用就是 重写 @Transactional 中的参数 timeout( 默认是-1)
//        // jdbcTemplate.setQueryTimeout(120);
//        /**
//         * 仅对 call 存过的返回值起作用 ，Map 的大小写不区分
//         */
//        jdbcTemplate.setResultsMapCaseInsensitive(true);
//
//        jdbcTemplate.afterPropertiesSet(); //其实没必要，spring 加载该Bean的时候，会自动执行
//        return jdbcTemplate;
//    }
//}
