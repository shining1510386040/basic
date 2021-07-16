//package com.demo.springboot.web.common.beanDI.condition;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Condition;
//import org.springframework.context.annotation.ConditionContext;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.type.AnnotatedTypeMetadata;
//import org.springframework.stereotype.Component;
//
///**
// * @author Wenyi Cao
// * @version 1.0
// * @link
// * @description VIP one 条件
// * @date 2021/2/24 14:47
// * @see
// */
//@Configuration
//@PropertySource("classpath:application-${spring.profiles.active}.yml")
//public class VipOneCondition implements Condition {
//
//    @Value("${stragety.type}")
//    private String type;
//
//    @Override
//    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
//
//        // todo ....这里什么取不到 配置的值呢？ type 为null
//        if (type.equalsIgnoreCase("vip1")) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//}
