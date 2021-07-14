package com.demo.springboot.dubboserviceapi.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 订单数据传输对象
 * @date 2021/7/1 16:48
 * @see
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto implements Serializable {

    private String orderId;
    private String orderName;
    private String orderDesc;
    private List<Object> productList;
    private double totalPrice;
    private Date createDate;
    private String createBy;
    private Date lastmodifyDate;
    private String lastmodifyBy;

}
