package com.demo.springboot.dbconnector.core.datasource;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 动态数据源类：
 * @date 2021/1/14 15:33
 * @see
 */
public class DynamicDataSource extends AbstractRoutingDataSource {


    /**
     * 确定目标数据源
     */
    @Override
    protected DataSource determineTargetDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        // 获取线程私有的key
        String key = DynamicDBUtil.getThreadKey();
        if ("dbschema".equals(key.split("#")[0])) {
            dataSource = DynamicDataSourceCache.initDataSourceByDbSchema(key.split("#")[1]);
        }

        if ("org".equals(key.split("#")[0])) {
            dataSource = DynamicDataSourceCache.initDataSourceByOrg(key.split("#")[1]);
        }

        return dataSource;
    }

    /**
     * 确定当前要查找的key
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDBUtil.getThreadKey();
    }

    /**
     * 设置默认数据源
     */
    public void setDefaultDataSource(Object defaultDataSource) {
        super.setDefaultTargetDataSource(defaultDataSource);
    }

    /**
     * 设置数据源
     */
    public void setDataSources(Map<Object, Object> dataSources) {
        super.setTargetDataSources(dataSources);
    }
}
