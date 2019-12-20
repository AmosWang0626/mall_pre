package com.mall.gateway.exception;

import com.mall.common.response.ExceptionCodePrefix;
import com.mall.common.response.IExceptionEnum;

/**
 * PROJECT: gateway
 * DESCRIPTION: note
 *
 * @author Daoyuan
 * @date 2018/12/11
 */
public enum OrderExceptionEnum implements IExceptionEnum {

    /**
     * error code message
     */
    SERVICE_BUSY("1000", "服务器繁忙，请稍后重试"),
    ERROR_PARAM_FORMAT("10001", "参数格式错误[{0}]"),

    REGISTER_PHONE_EMAIL_BOTH_NULL("1000", "邮箱手机号不能同时为空"),
    ;

    /**
     * @see OrderExceptionEnum#getCode() 加上前缀呦
     */
    private static final String PREFIX = ExceptionCodePrefix.ORDER;

    private final String code;

    private final String message;

    OrderExceptionEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return PREFIX + code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
