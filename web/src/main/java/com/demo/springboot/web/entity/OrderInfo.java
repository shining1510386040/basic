package com.demo.springboot.web.entity;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 订单实体
 * @date 2020/12/18 16:46
 * @see
 */
public class OrderInfo {

    private long id;
    private String orderName;
    private String orderDesc;
    private double totalPrice;

    public OrderInfo() {

    }

    public OrderInfo(long id, String orderName, String orderDesc, double totalPrice) {
        this.id = id;
        this.orderName = orderName;
        this.orderDesc = orderDesc;
        this.totalPrice = totalPrice;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderDesc() {
        return orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
