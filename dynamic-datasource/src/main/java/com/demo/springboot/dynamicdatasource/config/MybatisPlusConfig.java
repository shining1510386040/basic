package com.demo.springboot.dynamicdatasource.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.incrementer.IKeyGenerator;
import com.baomidou.mybatisplus.extension.incrementer.H2KeyGenerator;
import com.baomidou.mybatisplus.extension.incrementer.OracleKeyGenerator;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description mybatis-plus 分页插件配置
 * @date 2021/6/8 14:36
 * @see
 */
@Configuration
public class MybatisPlusConfig {


    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description mybatis 的拦截器
     * @date 2021/6/8 14:37
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 主键生成策略：key-sequence序列
     * 默认支持：
     * DB2KeyGenerator
     * H2KeyGenerator
     * KingbaseKeyGenerator
     * OracleKeyGenerator
     * PostgreKeyGenerator
     * @date 2021/6/8 14:46
     */
    @Bean
    public IKeyGenerator keyGenerator() {
        return new OracleKeyGenerator();
    }

//    @Bean(name = "db1")
//    @ConfigurationProperties(prefix = "spring.datasource.druid.db1")
//    public DataSource db1() {
//        return DruidDataSourceBuilder.create().build();
//    }
//
//    @Bean(name = "db2")
//    @ConfigurationProperties(prefix = "spring.datasource.druid.db2")
//    public DataSource db2() {
//        return DruidDataSourceBuilder.create().build();
//    }
//
//    /**
//     * 动态数据源配置
//     *
//     * @return
//     */
//    @Bean
//    @Primary
//    public DataSource multipleDataSource(@Qualifier("db1") DataSource db1,
//                                         @Qualifier("db2") DataSource db2) {
//        DynamicDataSource dynamicDataSource = new DynamicDataSource();
//        Map<Object, Object> targetDataSources = new HashMap<>();
//        targetDataSources.put(DBTypeEnum.db1.getValue(), db1);
//        targetDataSources.put(DBTypeEnum.db2.getValue(), db2);
//        dynamicDataSource.setTargetDataSources(targetDataSources);
//        dynamicDataSource.setDefaultTargetDataSource(db2); // 程序默认数据源，这个要根据程序调用数据源频次，经常把常调用的数据源作为默认
//        return dynamicDataSource;
//    }
//
//    @Bean("sqlSessionFactory")
//    public SqlSessionFactory sqlSessionFactory() throws Exception {
//        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
//        sqlSessionFactory.setDataSource(multipleDataSource(db1(), db2()));
//
//        MybatisConfiguration configuration = new MybatisConfiguration();
//        configuration.setJdbcTypeForNull(JdbcType.NULL);
//        configuration.setMapUnderscoreToCamelCase(true);
//        configuration.setCacheEnabled(false);
//        sqlSessionFactory.setConfiguration(configuration);
//        //PerformanceInterceptor(),OptimisticLockerInterceptor()
//        //添加分页功能
//        sqlSessionFactory.setPlugins(new Interceptor[]{
//                paginationInterceptor()
//        });
////        sqlSessionFactory.setGlobalConfig(globalConfiguration()); //注释掉全局配置，因为在xml中读取就是全局配置
//        return sqlSessionFactory.getObject();
//    }
//
// /*   @Bean
//    public GlobalConfiguration globalConfiguration() {
//        GlobalConfiguration conf = new GlobalConfiguration(new LogicSqlInjector());
//        conf.setLogicDeleteValue("-1");
//        conf.setLogicNotDeleteValue("1");
//        conf.setIdType(0);
//        conf.setMetaObjectHandler(new MyMetaObjectHandler());
//        conf.setDbColumnUnderline(true);
//        conf.setRefresh(true);
//        return conf;
//    }*/

}
