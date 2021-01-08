package com.demo.springboot.dubboserviceapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 订单Dto
 * @date 2021/1/8 14:49
 * @see
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto implements Serializable {

    private String orderId;
    private String orderName;
    private String productId;
    private BigDecimal totalPrice;
    private int count;
}
