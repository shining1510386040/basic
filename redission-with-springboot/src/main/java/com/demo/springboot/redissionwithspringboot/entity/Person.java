package com.demo.springboot.redissionwithspringboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 测试实体-人
 * @date 2021/12/6 17:12
 * @see
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    private int id;

    private String name;
}
