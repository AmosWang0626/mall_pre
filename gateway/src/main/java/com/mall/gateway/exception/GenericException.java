package com.mall.gateway.exception;

/**
 * PROJECT: gateway
 * DESCRIPTION: note
 *
 * @author Daoyuan
 * @date 2018/12/27
 */
public class GenericException extends RuntimeException {

    private static final long serialVersionUID = -3824666236775586776L;

    public GenericException(String message) {
        super(message);
    }
}
