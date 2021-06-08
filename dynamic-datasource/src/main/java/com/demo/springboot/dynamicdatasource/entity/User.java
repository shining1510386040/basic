package com.demo.springboot.dynamicdatasource.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 用户实体
 * @date 2021/6/8 10:50
 * @see
 */
@Data
@TableName("user")
public class User {

    @TableId("id")
    private Long id;
    @TableField("name")
    private String name;
    @TableField("age")
    private Integer age;
    @TableField("email")
    private String email;
}
