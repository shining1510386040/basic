package com.demo.springboot.dbconnector.core.datasource;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 数据源工厂
 * @date 2021/1/14 15:59
 * @see
 */
public class DynamicDataSourceCache extends PooledDataSource {


    private static Logger logger = LoggerFactory.getLogger(DynamicDataSourceCache.class);
    /**
     * 数据源内存map：初始化bean的时候会放入一个主数据源
     */
    public static ConcurrentHashMap<Object, Object> datasourceCache = new ConcurrentHashMap<>(16);


    /**
     * @param schema 数据库名
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 通过数据库名schema初始化数据源
     * @date 2021/1/14 16:06
     */
    public static HikariDataSource initDataSourceByDbSchema(String schema) {
        HikariDataSource dataSource = initDataSource("schema", schema);
        return dataSource;
    }

    /**
     * @param orgId 组织id
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 通过组织初始化数据源
     * @date 2021/1/14 16:08
     */
    public static HikariDataSource initDataSourceByOrg(String orgId) {
        HikariDataSource dataSource = initDataSource("org", orgId);
        return dataSource;
    }

    /**
     * @param type 类型
     * @param key  参数
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 初始化数据源，配置信息来源于主数据源master
     * @date 2021/1/14 16:09
     */
    private static HikariDataSource initDataSource(String type, String key) {
        // 内存map中存在数据源则返回，不存在初始化创建，并放入内存map
        if (null != datasourceCache.get(key)) {
            logger.info("获取数据源：[" + key + "] 从数据源cache!");
            return (HikariDataSource) datasourceCache.get(key);
        } else {
            HikariDataSource dataSource = new HikariDataSource();
            if ("schema".equals(type)) {
                dataSource = getCustomerDataSourceByDbSchema(key);
            }

            if ("org".equals(type)) {
                dataSource = getCustomerDataSourceByOrg(key);
            }

            logger.info("获取数据源：[" + key + "]从主master数据库！ ");
            datasourceCache.put(key, dataSource);
            return dataSource;
        }
    }

    private static HikariDataSource getCustomerDataSourceByDbSchema(String schema) {
        return getCustomerDataSource("schema", schema);
    }

    private static HikariDataSource getCustomerDataSourceByOrg(String orgId) {
        return getCustomerDataSource("org", orgId);
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 创建自定义数据源的方法
     * @date 2021/1/14 16:16
     */
    private static HikariDataSource getCustomerDataSource(String type, String key) {
        // 主数据源
        HikariDataSource dataSource = (HikariDataSource) datasourceCache.get("master");
        // 自定义数据源
        HikariDataSource customerDataSource = new HikariDataSource();

        try {
            Connection conn = dataSource.getConnection();
            Statement stmtTotal = conn.createStatement();
            String querySQL = "";
            if ("schema".equals(type)) {
                querySQL = "SELECT t.db_url AS dbUrl, t.driver_class_name AS driverClassName, t.query_timeout AS queryTimeout, t.database_username AS username, t.database_password AS PASSWORD FROM tp_other_datasource t WHERE t.database_name = '" + key + "'";
            }

            if ("org".equals(type)) {
                querySQL = "SELECT t.db_url AS dbUrl, t.driver_class_name AS driverClassName, t.query_timeout AS queryTimeout, t.database_username AS username, t.database_password AS PASSWORD FROM tp_other_datasource t WHERE t.belong_org = '" + key + "'";
            }

            ResultSet rs = stmtTotal.executeQuery(querySQL);
            String dburl = "";
            String username = "";
            String password = "";
            String driveClass = "";

            long querytimeout;
//            while (rs.next()){
//                // ....
//            }
            for (querytimeout = 0L; rs.next(); ) {
                querytimeout = rs.getLong("queryTimeout");
                dburl = rs.getString("dbUrl");
                username = rs.getString("username");
                password = rs.getString("password");
                driveClass = rs.getString("driverClassName");
            }

            customerDataSource.setJdbcUrl(dburl);
            customerDataSource.setUsername(username);
            customerDataSource.setPassword(password);
            customerDataSource.setDriverClassName(driveClass);
            customerDataSource.setConnectionTimeout(querytimeout * 1000L);
            customerDataSource.setMaximumPoolSize(5);

            // 关闭连接，释放资源
            stmtTotal.close();
            rs.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerDataSource;
    }


}
