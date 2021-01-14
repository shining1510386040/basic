package com.demo.springboot.dbconnector.config;

import com.demo.springboot.dbconnector.core.datasource.DynamicDataSource;
import com.demo.springboot.dbconnector.core.datasource.DynamicDataSourceCache;
import com.zaxxer.hikari.HikariDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description mybatis多数据源配置
 * @date 2021/1/14 14:53
 * @see
 */
@ConfigurationProperties(prefix = "spring.datasource")
@MapperScan(basePackages = {"com.demo.springboot.**.mapper"})
@Configuration
public class MybatisConfig {

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    public MybatisConfig() {
    }

    /**
     * 主数据源
     */
    @Bean({"master"})
    @Primary
    public DataSource master() {
        HikariDataSource dataSource = new HikariDataSource();

        try {
            // todo ...解密
//            DecryptUtils dcrypt = new DecryptUtils("");
            dataSource.setJdbcUrl(this.url);
            dataSource.setUsername(this.username);
            dataSource.setPassword(this.password);
            dataSource.setDriverClassName(this.driverClassName);
            dataSource.setConnectionTimeout(5000L);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    /**
     * 动态数据源：来源于主数据源中表（tp_other_datasource）配置：
     */
    @Bean({"dynamicDataSource"})
    public DataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        DynamicDataSourceCache.datasourceCache.put("master", this.master());
        // 默认数据源
        dynamicDataSource.setDefaultDataSource(this.master());
        // 其他数据源
        dynamicDataSource.setDataSources(DynamicDataSourceCache.datasourceCache);
        return dynamicDataSource;
    }

    /**
     * mybatis 连接工厂；
     */
    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(this.dynamicDataSource());
        sessionFactory.setTypeAliasesPackage("com.demo.springboot.**.mapper");
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        // 映射文件位置
        sessionFactory.setMapperLocations(resolver.getResources("classpath*:**/mappers/*.xml"));
        // 总配置文件位置
        sessionFactory.setConfigLocation((new DefaultResourceLoader()).getResource("classpath:mybatis-config.xml"));
        return sessionFactory;
    }

    /**
     * 事务管理器
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(this.dynamicDataSource());
    }

}
