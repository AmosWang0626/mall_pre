package com.mall.user.common.enums;

import com.mall.common.response.ExceptionCodePrefix;
import com.mall.common.response.IExceptionEnum;

/**
 * PROJECT: gateway
 * DESCRIPTION: note
 *
 * @author Daoyuan
 * @date 2018/12/11
 */
public enum UserExceptionEnum implements IExceptionEnum {

    /**
     * exception code message
     */
    REGISTER_ACCOUNT_EXISTED("1001", "账号已占用"),
    LOGIN_ACCOUNT_NOT_FOUND("1002", "账号未注册"),
    LOGIN_PASSWORD_ERROR("1003", "密码错误"),
    PHONE_NO_FORMAT_ERROR("1004", "手机号格式错误"),
    ;

    /**
     * @see UserExceptionEnum#getCode() 加上前缀呦
     */
    private static final String PREFIX = ExceptionCodePrefix.USER;

    private final String code;
    private final String message;

    UserExceptionEnum(String code, String message) {
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
