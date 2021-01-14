package com.demo.springboot.dbconnector.core.datasource;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 切换数据源工具类
 * @date 2021/1/14 15:45
 * @see
 */
public class DynamicDBUtil {
    private static final ThreadLocal<String> key = new ThreadLocal();

    public DynamicDBUtil() {
    }

    public static String getThreadKey() {
        return key.get();
    }

    /**
     * @param dbSchema 数据库名称
     * @author Wenyi Cao
     * @version 1.0
     * @description 设置数据库名称
     * @date 2021/1/14 15:49
     */
    public static void setDBSchema(String dbSchema) {
        key.set("dbschema#" + dbSchema);
    }

    /**
     * @param orgId 组织id
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 设置组织机构id
     * @date 2021/1/14 15:50
     */
    public static void setOrg(String orgId) {
        key.set("org#" + orgId);
    }

    /**
     * @author Wenyi Cao
     * @version 1.0
     * @description 清空
     * @date 2021/1/14 15:51
     */
    public static void clearDBSchema() {
        key.remove();
    }
}
