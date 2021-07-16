//package com.demo.springboot.web.common.beanDI.config;
//
//import com.demo.springboot.web.common.beanDI.VipFourStrategy;
//import com.demo.springboot.web.common.beanDI.VipOneStrategy;
//import com.demo.springboot.web.common.beanDI.VipThreeStrategy;
//import com.demo.springboot.web.common.beanDI.VipTwoStrategy;
//import com.demo.springboot.web.common.beanDI.condition.VipFourCondition;
//import com.demo.springboot.web.common.beanDI.condition.VipOneCondition;
//import com.demo.springboot.web.common.beanDI.condition.VipThreeCondition;
//import com.demo.springboot.web.common.beanDI.condition.VipTwoCondition;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Conditional;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @author Wenyi Cao
// * @version 1.0
// * @link
// * @description Bean 配置
// * @date 2021/2/24 15:03
// * @see
// */
//@Configuration
//public class BeanConfig {
//
//    @Bean
//    @Conditional(VipOneCondition.class)
//    public VipOneStrategy vipOneStrategy() {
//        return new VipOneStrategy();
//    }
//
//    @Bean
//    @Conditional(VipTwoCondition.class)
//    public VipTwoStrategy vipTwoStrategy() {
//        return new VipTwoStrategy();
//    }
//
//    @Bean
//    @Conditional(VipThreeCondition.class)
//    public VipThreeStrategy vipThreeStrategy() {
//        return new VipThreeStrategy();
//    }
//
//    @Bean
//    @Conditional(VipFourCondition.class)
//    public VipFourStrategy vipFourStrategy() {
//        return new VipFourStrategy();
//    }
//}
