package com.demo.springboot.consulesweb.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 分页查询结果数据
 * @date 2021/1/29 11:25
 * @see
 */
@Data
public class PageResult implements Serializable {

    /**
     * 总记录数
     */
    private long total;

    /**
     * 当前是第几页
     */
    private int currentPageNo;

    /**
     * 页容量
     */
    private int currentPageSize;

    /**
     * 当前页数据
     */
    private Object data;


}
