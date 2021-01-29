package com.demo.springboot.consulesweb.entity.jdbc;

import lombok.Data;
import org.springframework.data.annotation.Id;


/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description Jdbc 仓储实体：
 * 不需要太多注解：一个@Id即可，getter/setter 也可不要
 * javaBean --> mysql 表
 * @date 2021/1/29 9:57
 * @see
 */
@Data
public class ProductJdbc {

    @Id
    private long id;

    private String productName;

    private String productType;

    private String productDesc;

    private double totalPrice;
}
