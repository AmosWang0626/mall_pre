package com.mall.common.api;

import java.lang.annotation.*;

/**
 * @author amos
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Token {

    /**
     * 是否检查token，默认为true
     *
     * @return true: 校验; false: 不校验
     */
    boolean check() default true;

}
