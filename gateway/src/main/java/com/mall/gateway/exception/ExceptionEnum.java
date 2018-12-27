package com.mall.gateway.exception;

/**
 * PROJECT: gateway
 * DESCRIPTION: note
 *
 * @author Daoyuan
 * @date 2018/12/11
 */
public enum ExceptionEnum {

    /**
     * generic 0000 开头【业务系统禁用0000开头】
     */
    SERVICE_BUSY("SYS0000", "服务器繁忙，请稍后重试"),
    ERROR_PARAM_FORMAT("SYS0001", "参数格式错误[{0}]"),

    /**
     * user 1000 开头
     */
    REGISTER_PHONE_EMAIL_BOTH_NULL("USER1000", "邮箱手机号不能同时为空"),
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