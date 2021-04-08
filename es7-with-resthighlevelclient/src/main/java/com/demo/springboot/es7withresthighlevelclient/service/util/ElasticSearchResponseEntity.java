package com.demo.springboot.es7withresthighlevelclient.service.util;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description ES 分页查询返回结果封装
 * @date 2021/4/8 10:15
 * @see
 */
@Data
@ToString
public class ElasticSearchResponseEntity {

    // 查询总数
    private Long total;
    // 数据集合
    private List<String> list;

    public ElasticSearchResponseEntity(Long total, List<String> list) {
        this.total = total;
        this.list = list;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
