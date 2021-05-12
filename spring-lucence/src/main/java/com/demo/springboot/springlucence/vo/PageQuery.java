package com.demo.springboot.springlucence.vo;

import com.demo.springboot.springlucence.entity.WorkOrderRecord;
import com.github.pagehelper.PageInfo;
import lombok.Data;
import org.apache.lucene.search.Sort;

import java.util.List;
import java.util.Map;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description Lucene 查询参数封装
 * @date 2021/4/26 10:38
 * @see
 */
@Data
public class PageQuery {

    private PageInfo pageInfo;
    /**
     * 排序字段
     */
    private Sort sort;
    /**
     * 查询参数类
     */
    private WorkOrderRecord params;
    /**
     * 返回结果集
     */
    private List<WorkOrderRecord> results;
    /**
     * 不在T类中的参数
     */
    private Map<String, String> queryParam;

}
