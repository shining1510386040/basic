package com.demo.springboot.consulesweb.entity.jpa;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description Jpa 仓储实体：
 * javaBean --> mysql 表
 * NamedQueries： 命名查询
 * @date 2021/1/29 9:57
 * @see
 */
@Entity
@Table(name = "product_jpa")
@Data
@NamedQueries(value = {
        // 写Hql 语句
        @NamedQuery(name = "ProductJpa.findWithProductName", query = "select p from ProductJpa p where p.productname like concat(:productName,'%') ")
})
public class ProductJpa {


    /**
     * id主键：
     * GeneratedValue：主键生成策略，默认auto
     */
    @Id
    @GeneratedValue
    private long id;

    /**
     * 名称
     */
    @Column(name = "product_name")
    private String productname;

    /**
     * 规格
     */
    @Column(name = "product_type")
    private String producttype;

    @Column(name = "product_desc")
    private String productdesc;

    /**
     * 指定列名，和精度 (2位小数)
     */
    @Column(name = "total_price", precision = 2)
    private double totalprice;
}
