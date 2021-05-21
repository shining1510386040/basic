package com.demo.springboot.neo4j.entity;

import lombok.Builder;
import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 节点标签实体-供应商；
 * 有点类似于Mysql中的table 映射的对象类，mysql中叫做ORM，neo4j中叫做OGM [object graph mapping]）
 * @date 2021/5/21 10:53
 * @see
 */
@NodeEntity(label = "supply")
@Builder
@Data
public class SupplyGraph {

    @Id
    @GeneratedValue
    private Long id;

    /**
     * 供应商名称
     */
    @Property(name = "name")
    private String name;


}