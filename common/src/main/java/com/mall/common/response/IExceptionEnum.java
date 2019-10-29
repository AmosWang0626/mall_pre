package com.mall.common.response;

/**
 * PROJECT: mall
 * DESCRIPTION: 状态码 interface
 *
 * @author amos
 * @date 2019/10/29
 */
public interface IExceptionEnum {

    /**
     * 状态码
     *
     * @return 状态码
     */
    String getCode();

    /**
     * 状态码对应信息
     *
     * @return 状态码对应信息
     */
    String getMessage();

}
