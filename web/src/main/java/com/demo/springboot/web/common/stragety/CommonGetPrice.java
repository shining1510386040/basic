package com.demo.springboot.web.common.stragety;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 正常if-else 形式的写法；
 * 如果有新增类型，就需要频繁的修改此处的代码！
 * * 不符合开闭原则！
 * @date 2021/2/24 14:08
 * @see
 */
public class CommonGetPrice {

    public static final String VIP1 = "vip1";
    public static final String VIP2 = "vip2";
    public static final String VIP3 = "vip3";
    public static final String VIP4 = "vip4";

    /**
     * @param type       类型
     * @param orignPrice 原始价格
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 获取vip价格
     * @date 2021/2/24 14:11
     */
    public static double getVipPrice(String type, double orignPrice) {
        if (type.equals(VIP1)) {
            System.out.println("不使用策略模式---不打折,原价");
            return orignPrice;
        } else if (type.equals(VIP2)) {
            System.out.println("不使用策略模式---打九折");
            return orignPrice * 0.9;
        } else if (type.equals(VIP3)) {
            System.out.println("不使用策略模式---打八五折");
            return orignPrice * 0.85;
        } else if (type.equals(VIP4)) {
            System.out.println("不使用策略模式---打八折");
            return orignPrice * 0.8;
        }
        return orignPrice;
    }

}
