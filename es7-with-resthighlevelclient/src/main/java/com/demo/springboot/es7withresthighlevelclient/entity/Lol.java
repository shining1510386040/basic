package com.demo.springboot.es7withresthighlevelclient.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 测试实体类
 * @date 2021/4/7 17:11
 * @see
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Lol implements Serializable {

    private Long id;
    /**
     * 英雄游戏名字
     */
    private String name;
    /**
     * 英雄名字
     */
    private String realName;
    /**
     * 英雄描述信息
     */
    private String desc;
}