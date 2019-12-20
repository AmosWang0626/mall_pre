package com.mall.common.api;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * PROJECT: boot
 * DESCRIPTION: BLANK NULL 互转
 *
 * @author amos.wang
 * @date 2019/4/30
 */
@Target({ElementType.FIELD, ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface StringBlankNull {

    Convert convert() default Convert.B2N;

    enum Convert {
        /**
         * B2N 空字符串 转化为 NULL
         * N2B NULL 转化为 空字符串
         */
        B2N, N2B
    }

}
