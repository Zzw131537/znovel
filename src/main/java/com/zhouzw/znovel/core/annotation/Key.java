package com.zhouzw.znovel.core.annotation;

import java.lang.annotation.*;

/**
 * 分布式锁 - Key 注解
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.FIELD,ElementType.PARAMETER})
public @interface Key {

    String expr() default "";
}
