package com.demo.springboot.neo4j.entity;

import lombok.Builder;
import lombok.Data;
import org.neo4j.ogm.annotation.*;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 节点关系实体；供应关系
 * @date 2021/5/21 10:57
 * @see
 */
@Data
@Builder
@RelationshipEntity(type = "supply")
public class SupplyRelationship {

    @Id
    @GeneratedValue
    private Long id;

    @Property(name = "indexName")
    private String indexName;

    /**
     * 关系的一端节点是 公司节点
     */
    @StartNode
    private CompanyGraph company;

    /**
     * 关系的另一端节点是 供应商节点
     */
    @EndNode
    private SupplyGraph supply;

    //下面是关系的属性
    /**
     * 采购占比
     */
    @Property(name = "scale")
    private String scale;

    /**
     * 采购金额
     */
    @Property(name = "amount")
    private String amount;
}
