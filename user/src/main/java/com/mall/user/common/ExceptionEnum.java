package com.mall.user.common;

/**
 * PROJECT: gateway
 * DESCRIPTION: note
 *
 * @author Daoyuan
 * @date 2018/12/11
 */
public enum ExceptionEnum {

    /**
     * user 10000 开头
     */
    REGISTER_ALREADY_PHONE_EMAIL("10000", "手机号或邮箱已被注册"),
    REGISTER_FAIL_DB_ERROR("10001", "注册失败，服务器异常"),
    LOGIN_ACCOUNT_NOT_FOUND("10002", "账号未注册"),
    LOGIN_PASSWORD_ERROR("10003", "密码错误"),
    PHONE_NO_FORMAT_ERROE("10004", "手机号格式错误"),
    ;

    private final String code;

    private final String message;

    ExceptionEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
