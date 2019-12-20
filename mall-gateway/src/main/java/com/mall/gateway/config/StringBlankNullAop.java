package com.mall.gateway.config;

import com.mall.common.api.StringBlankNull;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Field;

/**
 * PROJECT: boot
 * DESCRIPTION: 拦截@StringBlankNull注解
 *
 * @author amos.wang
 * @date 2019/4/30
 * @see StringBlankNull
 */
@Aspect
@Configuration
public class StringBlankNullAop {

    @Before("@annotation(stringBlankNull)")
    public void doCheck(JoinPoint joinPoint, StringBlankNull stringBlankNull) {
        Object[] args = joinPoint.getArgs();
        for (Object obj : args) {
            try {
                checkClassParam(obj, stringBlankNull.convert());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 判断对象是不是自定义对象
     *
     * @param object 对象
     * @return 是自定义对象 return true
     */
    private static boolean isSelfClass(Object object) {
        Class<?> clazz = object.getClass();
        if (clazz.getPackage() == null) {
            return false;
        }
        return clazz.getPackage().toString().contains("com.amos");
    }

    private void checkClassParam(Object object, StringBlankNull.Convert convert) throws IllegalAccessException {
        if (!isSelfClass(object)) {
            return;
        }
        Field[] declaredFields = object.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            if (String.class != field.getType()) {
                continue;
            }
            // 类中的成员变量为private,故必须进行此操作
            field.setAccessible(true);
            String fieldValue = (String) field.get(object);
            if (StringBlankNull.Convert.B2N.equals(convert)) {
                if (StringUtils.isBlank(fieldValue)) {
                    field.set(object, null);
                }
            } else if (StringBlankNull.Convert.N2B.equals(convert)) {
                if (fieldValue == null) {
                    field.set(object, "");
                }
            }
        }
    }

}
