package com.demo.springboot.shardingspherewithspringboot.vo;

import lombok.Builder;
import lombok.Data;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description vue前端-分页后每一条数据封装
 * @date 2021/6/1 19:25
 * @see
 */
@Data
@Builder
public class CommTableItemVo {

    /**
     * 表头展示值
     */
    private String title;
    /**
     * 具体每一项的值
     */
    private String value;

    /**
     * item布局类型
     */
    private String layoutType;

    /**
     * 每一项的唯一id值
     */
    private String id;
}
