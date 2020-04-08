package com.mall.user.web.exception;

/**
 * 模块名称: mall
 * 模块描述: 账号错误异常
 *
 * @author amos.wang
 * @date 2020/4/8 18:23
 */
public class AccountErrorException extends RuntimeException {

    private static final long serialVersionUID = 6269953626126610680L;

    public AccountErrorException(String message) {
        super(message);
    }

}
