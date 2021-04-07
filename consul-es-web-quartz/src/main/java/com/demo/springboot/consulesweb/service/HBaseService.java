package com.demo.springboot.consulesweb.service;

import com.demo.springboot.consulesweb.utils.HbaseTable;
import com.demo.springboot.consulesweb.utils.Result;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description Hbase 服务
 * @date 2021/2/23 20:30
 * @see
 */
public interface HBaseService {

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 创建表
     * @date 2021/2/24 15:47
     */
    Result createTable(HbaseTable hbaseTable) throws Exception;
}
