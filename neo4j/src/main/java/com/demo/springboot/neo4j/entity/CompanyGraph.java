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
 * @description 公司标签节点
 * @date 2021/5/21 11:02
 * @see
 */
@NodeEntity(label = "company")
@Data
@Builder
public class CompanyGraph {

    @Id
    @GeneratedValue
    private Long id;

    /**
     * 简称
     */
    @Property(name = "name")
    private String name;

    /**
     * 全称
     */
    @Property(name = "fullName")
    private String fullName;
}
