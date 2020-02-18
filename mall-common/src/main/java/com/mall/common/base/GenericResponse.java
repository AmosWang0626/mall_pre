package com.mall.common.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.client.naming.utils.CollectionUtils;
import com.mall.common.constant.WordConstant;
import org.springframework.validation.ObjectError;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.List;

/**
 * PROJECT: gateway
 * DESCRIPTION: note
 *
 * @author Daoyuan
 * @date 2018/12/11
 */
public class GenericResponse<T> implements Serializable {

    private static final long serialVersionUID = -8600893080428359242L;

    public static final GenericResponse<String> SUCCESS = new GenericResponse<>("1000", WordConstant.SUCCESS);
    public static final GenericResponse<String> FAIL = new GenericResponse<>("1001", WordConstant.FAIL);
    public static final GenericResponse<String> ERROR_PARAM = new GenericResponse<>("1002", WordConstant.ERROR_PARAM);
    public static final GenericResponse<String> SYSTEM_ERROR = new GenericResponse<>("1003", WordConstant.SYSTEM_ERROR);
    public static final GenericResponse<String> REQUEST_ILLEGAL = new GenericResponse<>("1004", WordConstant.REQUEST_ILLEGAL);


    /**
     * 状态码
     */
    private String code;
    /**
     * 状态信息
     */
    private String message;
    /**
     * 对应body
     */
    private T body;

    public GenericResponse() {
    }

    public GenericResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public GenericResponse(String code, String message, T body) {
        this.code = code;
        this.message = message;
        this.body = body;
    }

    public GenericResponse(T body) {
        this(SUCCESS.getCode(), SUCCESS.getMessage(), body);
    }

    public GenericResponse(IExceptionEnum exceptionEnum) {
        this(exceptionEnum.getCode(), exceptionEnum.getMessage(), null);
    }

    public GenericResponse(IExceptionEnum exceptionEnum, List<ObjectError> list) {
        this.setCode(exceptionEnum.getCode());
        this.setMessage(exceptionEnum.getMessage());
        if (!CollectionUtils.isEmpty(list)) {
            this.setMessage(MessageFormat.format(this.getMessage(), list.get(0).getDefaultMessage()));
        }
    }

    /**
     * 处理泛型类型不一致的异常
     *
     * @param response 原始 GenericResponse<F>
     * @param <T>      返回 response 的类型
     * @param <F>      入参 response 的类型
     * @return GenericResponse<T>
     */
    public static <T, F> GenericResponse<T> format(GenericResponse<F> response) {
        return new GenericResponse<>(response.getCode(), response.getMessage());
    }

    public boolean isSuccessful() {
        return SUCCESS.getCode().equals(this.getCode());
    }

    public boolean unSuccessful() {
        return !isSuccessful();
    }

    public String getCode() {
        return code;
    }

    private void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    private void setMessage(String message) {
        this.message = message;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
