package com.demo.springboot.web.common.stragety.stragety;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 策略接口；
 * 就是个普通的接口；可以有不同的实现；
 * @date 2021/2/24 14:14
 * @see
 */
public interface Stragety {

    double getVipPrice(double originPrice);
}
