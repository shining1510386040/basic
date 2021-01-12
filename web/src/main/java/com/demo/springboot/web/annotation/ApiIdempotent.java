package com.demo.springboot.web.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 幂等性校验注解，标记注解；
 * 标记在需要幂等性校验的controller方法上
 * @date 2021/1/12 17:02
 * @see
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ApiIdempotent {
}
