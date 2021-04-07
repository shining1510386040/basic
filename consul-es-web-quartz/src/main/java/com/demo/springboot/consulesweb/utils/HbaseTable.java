package com.demo.springboot.consulesweb.utils;

import java.util.List;
import java.util.Map;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description Hbase htable 参数封装
 * @date 2021/2/24 15:47
 * @see
 */
public class HbaseTable {

    /**
     * 表名
     */
    private String tableName;

    /**
     * family封装
     */
    private List<Map<String, String>> columnMapList;

    public String getTableName() {
        return tableName;
    }

    public List<Map<String, String>> getColumnMapList() {
        return columnMapList;
    }
}
