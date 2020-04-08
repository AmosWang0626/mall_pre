package com.mall.user.web.exception;

import com.mall.common.base.GenericResponse;
import com.mall.user.common.enums.UserExceptionEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * PROJECT: mall-user
 * DESCRIPTION: ExceptionAdvice
 *
 * @author Daoyuan
 * @date 2018/12/11
 */
@ControllerAdvice
public class ExceptionAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionAdvice.class);

    @ResponseBody
    @ExceptionHandler(value = AccountErrorException.class)
    public GenericResponse<String> handleThrowable(Throwable e) {
        LOGGER.error("{} \n位置: {}", e.toString(), e.getStackTrace()[0]);
        e.printStackTrace();
        return new GenericResponse<>(UserExceptionEnum.LOGIN_ACCOUNT_NOT_FOUND);
    }

}
