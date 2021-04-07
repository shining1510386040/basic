package com.demo.springboot.web.common.stragety.stragety;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 策略上下文：
 * 负责和具体的策略类交互
 * * 这样的话，具体的算法和直接的客户端调用分离了，使得算法可以独立于客户端独立的变化。
 * * 如果使用spring的依赖注入功能，还可以通过配置文件，动态的注入不同策略对象，动态的切换不同的算法.
 * （根据配置的不同（开关type）注入不同的接口实现类Bean）
 * @date 2021/2/24 14:17
 * @see
 */
public class VipContext {

    // has a
    private Stragety stragety;

    public VipContext() {

    }

    /**
     * 可以通过构造器来注入
     */
    public VipContext(Stragety strategy) {
        this.stragety = strategy;
    }

    /**
     * 可以通过set方法来注入
     */
    public void setStrategy(Stragety strategy) {
        this.stragety = strategy;
    }

    /**
     * 提供业务方法：use a
     */
    public double getVipPrice(double originPrice) {
        // use a
        return stragety.getVipPrice(originPrice);
    }

}
