package com.demo.springboot.shardingspherewithspringboot.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 数据表格分页vo
 * @date 2021/6/1 11:19
 * @see
 */
@Data
@Builder
public class CommTablePageVo {

    /**
     * 第几页
     */
    private Long pageNo;
    /**
     * 页容量
     */
    private Long pageSize;
    /**
     * 总页数
     */
    private Long totalPages;
    /**
     * 数据总数
     */
    private Long total;
    /**
     * 当前页数据
     */
    private List list;

    /**
     * 当前表头数据集合
     */
    private List title;
}
