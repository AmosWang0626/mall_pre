package com.mall.gateway.common.exception;

import com.mall.common.base.ExceptionCodePrefix;
import com.mall.common.base.IExceptionEnum;

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
    ERROR_PARAM_FORMAT("10001", "参数格式错误[{0}]"),
    /**
     * user
     */
    USER_ACCOUNT_NOT_EXIST("10011", "账号不存在"),
    USER_TOKEN_EXPIRED("10012", "Token已过期"),
    USER_ACCOUNT_LOGIN_ELSEWHERE("10013", "您的账号在其他地方登录, 请重新登录"),
    ;

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
