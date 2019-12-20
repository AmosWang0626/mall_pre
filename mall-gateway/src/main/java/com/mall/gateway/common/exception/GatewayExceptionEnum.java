package com.mall.gateway.common.exception;

import com.mall.common.response.ExceptionCodePrefix;
import com.mall.common.response.IExceptionEnum;

/**
 * PROJECT: gateway
 * DESCRIPTION: note
 *
 * @author Daoyuan
 * @date 2018/12/11
 */
public enum GatewayExceptionEnum implements IExceptionEnum {

    /**
     * error code message
     */
    ERROR_PARAM_FORMAT("10001", "参数格式错误[{0}]");

    /**
     * @see GatewayExceptionEnum#getCode() 加上前缀呦
     */
    private static final String PREFIX = ExceptionCodePrefix.GATEWAY;

    private final String code;

    private final String message;

    GatewayExceptionEnum(String code, String message) {
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
