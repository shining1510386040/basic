package com.demo.springboot.springlucence.service;


import com.demo.springboot.springlucence.entity.WorkOrderRecord;
import com.demo.springboot.springlucence.vo.PageQuery;

import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description Lucene接口服务
 * @date 2021/4/26 10:32
 * @see
 */
public interface LuceneService {

    /**
     * 增加索引（工单）
     *
     * @param list
     * @throws IOException
     */
    void createWorkOrderRecordIndex(List<LinkedHashMap> list) throws IOException;

    /**
     * 查询
     *
     * @param pageQuery
     * @return
     * @throws Exception
     * @throws ParseException
     */
    PageQuery searchWorkOrderRecord(PageQuery pageQuery) throws Exception, ParseException;

    /**
     * 删除索引（工单）
     *
     * @param id
     * @throws IOException
     */
    void deleteWorkOrderRecordIndexById(String id) throws IOException;
}
