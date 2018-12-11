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
     * generic
     */
    SERVICE_BUSY("SYS2000", "服务器繁忙，请稍后再试。"),
    ERROR_PARAM_FORMAT("SYS2001", "参数格式错误[{0}]"),

    /**
     * user
     */
    ERROR_PARAM_FORMAT1("SYS2001", "参数格式错误[{0}]"),


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
