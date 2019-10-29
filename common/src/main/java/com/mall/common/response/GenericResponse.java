package com.mall.common.response;

import com.alibaba.fastjson.JSON;
import org.springframework.util.CollectionUtils;
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

    public static final GenericResponse SUCCESS = new GenericResponse("1000", "成功!");
    public static final GenericResponse FAIL = new GenericResponse("1001", "失败!");
    public static final GenericResponse ERROR_PARAM = new GenericResponse("1002", "参数错误!");
    public static final GenericResponse SERVER_EXCEPTION = new GenericResponse("1003", "系统异常!");
    public static final GenericResponse ILLEGAL = new GenericResponse("1004", "非法请求!");


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

    public GenericResponse(T body) {
        this.code = SUCCESS.getCode();
        this.message = SUCCESS.getMessage();
        this.body = body;
    }

    public GenericResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public GenericResponse(IExceptionEnum exceptionEnum) {
        this.code = exceptionEnum.getCode();
        this.message = exceptionEnum.getMessage();
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
        return JSON.toJSONString(this);
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

}
