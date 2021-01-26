package com.demo.springboot.consulesweb.entity.mongo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description mongo pojo
 * javaBean --> document 文档（表）
 * @date 2021/1/26 19:17
 * @see
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "order")
public class Order {

    @Id
    private String id;
    private String orderName;
    private double price;
}
