package com.mall.gateway.common.aop;

import com.mall.common.api.StringBlankNull;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Field;

/**
 * PROJECT: common
 * DESCRIPTION: 拦截 @StringBlankNull 注解
 *
 * @author amos.wang
 * @date 2019/4/30
 * @see StringBlankNull
 */
@Aspect
@Configuration
public class StringBlankNullAop {

    /**
     * 只校验该目录下的对象
     */
    private static final String CHECKED_PACKAGE = "com.amos";


    @Before("@annotation(stringBlankNull)")
    public void doCheck(JoinPoint joinPoint, StringBlankNull stringBlankNull) {
        Object[] args = joinPoint.getArgs();
        for (Object obj : args) {
            try {
                if (isCheckedPackage(obj)) {
                    checkClassParam(obj, stringBlankNull.convert());
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 只校验指定目录下的对象
     *
     * @param object 对象
     * @return 是指定目录下的对象 return true
     */
    private static boolean isCheckedPackage(Object object) {
        Class<?> clazz = object.getClass();
        if (clazz.getPackage() == null) {
            return false;
        }
        return clazz.getPackage().toString().contains(CHECKED_PACKAGE);
    }

    private void checkClassParam(Object object, StringBlankNull.Convert convert) throws IllegalAccessException {
        Field[] declaredFields = object.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            if (String.class.equals(field.getType())) {
                continue;
            }

            // 类中的成员变量为private,故必须进行此操作
            field.setAccessible(true);
            String fieldValue = (String) field.get(object);
            if (StringBlankNull.Convert.B2N.equals(convert)) {
                // "" -> null
                if (StringUtils.isBlank(fieldValue)) {
                    field.set(object, null);
                }
            } else if (StringBlankNull.Convert.N2B.equals(convert)) {
                // null -> ""
                if (fieldValue == null) {
                    field.set(object, "");
                }
            }
        }
    }

}
