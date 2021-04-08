package com.demo.springboot.es7withresthighlevelclient.service.util;

import lombok.Getter;
import lombok.Setter;
import org.elasticsearch.index.query.BoolQueryBuilder;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 封装es查询条件
 * @date 2021/4/8 10:07
 * @see
 */
@Getter
@Setter
public abstract class BaseElasticSearchEntity {

    /**
     * ES的 index (索引)
     */
    private String esIndex;
    /**
     * ES的 type(类型)：_doc
     * es7 中去掉此概念，
     * es6 中1个index库只能有一个type
     * es5 中1个index库中可有多个type
     */
    @Deprecated
    private String esType;
    /**
     * ES唯一值 id
     */
    private String esId;

    // 开始时间
    protected String startTime;

     // 结束时间
    protected String endTime;



    protected BaseElasticSearchEntity() {
    }

    protected BaseElasticSearchEntity(String esIndex) {
        this.esIndex = esIndex;
    }

    /**
     * 供子类封装查询条件
     *
     * @param boolQueryBuilder bool条件
     */
    public abstract void packageElasticSearchBody(BoolQueryBuilder boolQueryBuilder);
}
