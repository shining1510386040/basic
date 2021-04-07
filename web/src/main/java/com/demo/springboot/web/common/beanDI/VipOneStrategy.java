package com.demo.springboot.web.common.beanDI;

import com.demo.springboot.web.common.beanDI.condition.VipFourCondition;
import com.demo.springboot.web.common.beanDI.condition.VipOneCondition;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 具体策略实现if-else 的一个分支
 * @date 2021/2/24 14:35
 * @see
 */
public class VipOneStrategy implements Stragety {

    /**
     * vip1 不打折
     *
     * @param originPrice
     * @return
     */
    @Override
    public double getVipPrice(double originPrice) {

        System.out.println("使用策略模式---不打折,原价");
        return originPrice;
    }
}
