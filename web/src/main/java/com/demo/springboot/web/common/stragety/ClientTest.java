package com.demo.springboot.web.common.stragety;

import com.demo.springboot.web.common.stragety.stragety.*;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 策略模式代替if-else
 * @date 2021/2/24 14:38
 * @see
 */
public class ClientTest {

    public static void main(String[] args) {

        double originPrice = 1000D;

        //使用策略模式之前
        System.out.println(CommonGetPrice.getVipPrice(CommonGetPrice.VIP1, originPrice));
        System.out.println(CommonGetPrice.getVipPrice(CommonGetPrice.VIP2, originPrice));
        System.out.println(CommonGetPrice.getVipPrice(CommonGetPrice.VIP3, originPrice));
        System.out.println(CommonGetPrice.getVipPrice(CommonGetPrice.VIP4, originPrice));


        //使用策略模式之后
        VipContext vipContext = new VipContext();

        Stragety v1 = new VipOneStrategy();
        vipContext.setStrategy(v1);
        System.out.println(vipContext.getVipPrice(originPrice));

        Stragety v2 = new VipTwoStrategy();
        vipContext.setStrategy(v2);
        System.out.println(vipContext.getVipPrice(originPrice));

        Stragety v3 = new VipThreeStrategy();
        vipContext.setStrategy(v3);
        System.out.println(vipContext.getVipPrice(originPrice));

        Stragety v4 = new VipFourStrategy();
        vipContext.setStrategy(v4);
        System.out.println(vipContext.getVipPrice(originPrice));

    }
}
