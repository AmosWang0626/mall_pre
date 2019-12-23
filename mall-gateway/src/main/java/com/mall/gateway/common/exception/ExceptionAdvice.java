package com.mall.gateway.common.exception;

import com.mall.common.base.GenericResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * PROJECT: gateway
 * DESCRIPTION: note
 *
 * @author Daoyuan
 * @date 2018/12/11
 */
@ControllerAdvice
public class ExceptionAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionAdvice.class);

    @ResponseBody
    @ExceptionHandler(value = Throwable.class)
    public GenericResponse handleThrowable(Throwable e) {
        LOGGER.error("{} \n位置: {}", e.toString(), e.getStackTrace()[0]);
        e.printStackTrace();
        return GenericResponse.FAIL;
    }
    
    
    
}
