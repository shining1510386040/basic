package com.demo.springboot.web.common.beanDI;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 具体策略实现if-else 的一个分支
 * @date 2021/2/24 14:35
 * @see
 */
//@Component
//@Conditional(VipTwoCondition.class)
public class VipTwoStrategy implements Stragety {

    /**
     * vip1 不打折
     *
     * @param originPrice
     * @return
     */
    @Override
    public double getVipPrice(double originPrice) {

        System.out.println("使用策略模式---打九折");
        return originPrice * 0.9;
    }
}
