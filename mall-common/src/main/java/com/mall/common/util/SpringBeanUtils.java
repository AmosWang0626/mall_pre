package com.mall.common.util;

import org.springframework.context.ApplicationContext;

/**
 * PROJECT: estate
 * DESCRIPTION: 静态获取 bean
 *
 * @author amos
 * @date 2019/8/31
 */
public class SpringBeanUtils {

    private static ApplicationContext applicationContext;

    public SpringBeanUtils(ApplicationContext applicationContext) {
        SpringBeanUtils.applicationContext = applicationContext;
    }

    public static <T> T getBean(String name, Class<T> requiredType) {
        return applicationContext.getBean(name, requiredType);
    }

    public static <T> T getBean(Class<T> requiredType) {
        return applicationContext.getBean(requiredType);
    }

}
