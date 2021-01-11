package com.demo.springboot.web.annotation;

import java.lang.annotation.*;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 接口防刷注解：
 * 限制每天用户只能访问N次接口；
 * @Target 注解可修饰的对象范围，ElementType.METHOD 作用于方法，ElementType.TYPE 作用于类
 * * (ElementType)取值有：
 * * 　　　　1.CONSTRUCTOR:构造器
 * * 　　　　2.FIELD:域(属性)
 * * 　　　　3.LOCAL_VARIABLE:局部变量
 * * 　　　　4.METHOD:方法
 * * 　　　　5.PACKAGE:包
 * * 　　　　6.PARAMETER:参数
 * * 　　　　7.TYPE:类、接口(包括注解类型) 或enum声明
 * * @Retention定义了该Annotation被保留的时间长短：某些Annotation仅出现在源代码中，而被编译器丢弃；
 * * 而另一些却被编译在class文件中；编译在class文件中的Annotation可能会被虚拟机忽略，
 * * 而另一些在class被装载时将被读取（请注意并不影响class的执行，因为Annotation与class在使用上是被分离的）。
 * * 使用这个meta-Annotation可以对 Annotation的“生命周期”限制。
 * * （RetentionPoicy）取值有：
 * * 　　　　1.SOURCE:在源文件中有效（即源文件保留）
 * * 　　　　2.CLASS:在class文件中有效（即class保留）
 * * 　　　　3.RUNTIME:在运行时有效（即运行时保留）
 * *
 * * @Inherited
 * * 元注解是一个标记注解，@Inherited阐述了某个被标注的类型是被继承的。
 * * 如果一个使用了@Inherited修饰的annotation类型被用于一个class，则这个annotation将被用于该class的子类。
 * @date 2021/1/11 9:59
 * @see
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestLimit {
    // 在second秒内，最大只能请求 maxCount 次
    int second() default 1;

    int maxCount() default 1;

}
