package com.mall.gateway.common.exception;

/**
 * DESCRIPTION: 请求接口认证失败 Exception
 *
 * @author <a href="mailto:amos.wang@xiaoi.com">amos.wang</a>
 * @date 2019/12/21
 */
public class AuthException extends RuntimeException {

    private static final long serialVersionUID = 1799641880640493014L;

    public AuthException(String message) {
        super(message);
    }

}
