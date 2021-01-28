package com.demo.springboot.consulesweb.entity.es;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description es测试类pojo
 * <p>
 * javaBean --> es 中index （表）
 * @date 2021/1/26 18:54
 * @see
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "person")
public class Person {

    @Id
    private String id;
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
