package com.demo.springboot.rocketmqwithspringboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 消息实体-订单
 * @date 2021/12/10 12:02
 * @see
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private String orderId;
    private String orderDesc;
    private String orderName;

}
