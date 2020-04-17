package com.mall.gateway.common.exception;

import com.mall.common.base.GenericResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.WebExchangeBindException;

import javax.xml.bind.ValidationException;
import java.lang.reflect.UndeclaredThrowableException;

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
    public GenericResponse<String> handleThrowable(Throwable e) {
        LOGGER.error("{} \n位置: {}", e.toString(), e.getStackTrace()[0]);
        e.printStackTrace();
        return GenericResponse.FAIL;
    }

    @ResponseBody
    @ExceptionHandler(value = UndeclaredThrowableException.class)
    public GenericResponse<String> handleUndeclaredThrowable(Throwable e) {
        LOGGER.error("{} \n操作过于频繁: {}", e.toString(), e.getStackTrace()[0]);
        return GenericResponse.OPERATION_FREQUENTLY;
    }

    @ResponseBody
    @ExceptionHandler({ValidationException.class, MethodArgumentNotValidException.class, WebExchangeBindException.class})
    public GenericResponse<String> handleValidationThrowable(Throwable ex) {
        LOGGER.error("{} \n参数校验异常: {}", ex.toString(), ex.getStackTrace()[0]);
        if (ex instanceof MethodArgumentNotValidException) {
            return GenericResponse.ERROR_PARAM_BY.format(((MethodArgumentNotValidException) ex).getBindingResult().getAllErrors().get(0).getDefaultMessage());
        } else if (ex instanceof WebExchangeBindException) {
            return GenericResponse.ERROR_PARAM_BY.format(((WebExchangeBindException) ex).getAllErrors().get(0).getDefaultMessage());
        } else if (ex instanceof ValidationException) {
            return GenericResponse.ERROR_PARAM_BY.format(ex.getMessage());
        } else {
            return GenericResponse.ERROR_PARAM;
        }
    }

}
