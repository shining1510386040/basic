package com.demo.springboot.springlucence.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description mongo 工单问题分类
 * @date 2021/2/24 17:01
 * @see
 */
@Document("WorkOrderCategory")
@Data
public class WorkOrderCategory {

    @Id
    private String id;

    /**
     * 问题分类名称
     */
    @Field("category_name")
    private String categoryName;

    /**
     * 所属产品-冗余
     */
    @Field("belong_product")
    private String belongProduct;


}
