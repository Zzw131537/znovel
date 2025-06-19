package com.zhouzw.znovel.core.annotation;

import com.zhouzw.znovel.core.common.constant.ErrorCodeEnum;

import java.lang.annotation.*;

/**
 * 分布式锁注解
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Lock {

    String prefix();

    boolean isWait() default false;
    long waitTime() default 3L;

    ErrorCodeEnum failCode() default ErrorCodeEnum.OK;
}
