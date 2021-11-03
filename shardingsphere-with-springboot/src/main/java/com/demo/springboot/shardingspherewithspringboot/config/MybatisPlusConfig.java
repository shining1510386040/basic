package com.demo.springboot.shardingspherewithspringboot.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.incrementer.IKeyGenerator;
import com.baomidou.mybatisplus.core.toolkit.GlobalConfigUtils;
import com.baomidou.mybatisplus.extension.incrementer.PostgreKeyGenerator;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.LocalDateTimeTypeHandler;
import org.apache.ibatis.type.LocalDateTypeHandler;
import org.apache.ibatis.type.LocalTimeTypeHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description
 * @date 2021/11/3 15:23
 * @see
 */
@Slf4j
@Configuration
@MapperScan("com.demo.springboot.shardingspherewithspringboot.mapper")
public class MybatisPlusConfig {

//    public MybatisPlusConfig(MybatisPlusProperties mybatisPlusProperties) {
//        MybatisConfiguration configuration = mybatisPlusProperties.getConfiguration();
//        GlobalConfig globalConfig = mybatisPlusProperties.getGlobalConfig();
//        GlobalConfigUtils.setGlobalConfig(configuration, globalConfig);
//        configuration.getTypeHandlerRegistry().register(LocalDateTime.class, new LocalDateTimeTypeHandler());
//        configuration.getTypeHandlerRegistry().register(LocalDate.class, new LocalDateTypeHandler());
//        configuration.getTypeHandlerRegistry().register(LocalTime.class, new LocalTimeTypeHandler());
//        GlobalConfigUtils.setGlobalConfig(configuration, globalConfig);
//    }

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
        return new PostgreKeyGenerator();
    }
}
