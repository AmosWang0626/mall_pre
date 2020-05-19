package com.mall.common.api;

import java.lang.annotation.*;

/**
 * PROJECT: common
 * DESCRIPTION: null 和 "" 互转
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
         * "" -> null
         */
        B2N,
        /**
         * null -> ""
         */
        N2B
    }

}
