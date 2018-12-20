package com.mall.gateway.exception;

import org.springframework.util.CollectionUtils;
import org.springframework.validation.ObjectError;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Iterator;
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

    /**
     * 系统级状态码，其他状态码禁用 0 开头
     */
    public static final GenericResponse SUCCESS = new GenericResponse("0000", "成功!");
    public static final GenericResponse FAIL = new GenericResponse("0001", "失败!");
    public static final GenericResponse ERROR_PARAM = new GenericResponse("0002", "参数异常!");
    public static final GenericResponse ILLEGAL = new GenericResponse("0003", "非法请求!");

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

    public GenericResponse(T body) {
        this.code = SUCCESS.getCode();
        this.message = SUCCESS.getMessage();
        this.body = body;
    }

    public GenericResponse(ExceptionEnum exceptionEnum) {
        this.code = exceptionEnum.getCode();
        this.message = exceptionEnum.getMessage();
    }

    private GenericResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 将多个参数合成一个 String，然后 setMessage
     *
     * @param params 多个参数
     * @return GenericResponse
     */
    public GenericResponse buildParam(String... params) {
        if (params != null && params.length > 0) {
            StringBuilder sb = new StringBuilder();
            Iterator<String> iterator = Arrays.asList(params).iterator();
            while (iterator.hasNext()) {
                sb.append(iterator.next());
                while (iterator.hasNext()) {
                    sb.append(", ");
                }
            }
            this.setMessage(MessageFormat.format(this.getMessage(), sb.toString()));
        }

        return this;
    }

    /**
     * 将多个 ObjectError.getDefaultMessage 合成一个 String，然后 setMessage
     *
     * @param list 多个参数
     * @return GenericResponse
     * @see ObjectError#getDefaultMessage()
     */
    public GenericResponse buildParam(List<ObjectError> list) {
        if (CollectionUtils.isEmpty(list)) {
            return this;
        }
        this.setMessage(MessageFormat.format(this.getMessage(), list.get(0).getDefaultMessage()));

        return this;
    }

    public boolean successful() {
        return SUCCESS.getCode().equalsIgnoreCase(this.getCode());
    }

    public boolean failure() {
        return !successful();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
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
        return this.code + " " + this.message;
    }
}
