package com.demo.springboot.commonsapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 订单
 * @date 2021/2/3 19:10
 * @see
 */
@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {

    private String orderId;
    private String orderName;

}
