package com.mall.user.common;

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
    REGISTER_ALREADY_PHONE_EMAIL("1000", "手机号或邮箱已被注册"),
    REGISTER_FAIL_DB_ERROR("1001", "注册失败，服务器异常"),
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
