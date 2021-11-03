package com.demo.springboot.shardingspherewithspringboot.vo;

import lombok.Builder;
import lombok.Data;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description vue前端- 单行表头
 * @date 2021/6/1 19:50
 * @see
 */
@Data
@Builder
public class CommTableTitle {

    /**
     * 第几个表头-索引，从0开始
     */
    private Long index;

    /**
     * 是否可排序
     */
    private Boolean sortable;

    /**
     * 表头展示值
     */
    private String title;

    /**
     * 表头对应的数据key
     */
    private String prop;
}
