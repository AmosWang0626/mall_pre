package com.mall.user.common.pojo.response;

import lombok.Data;

import java.io.Serializable;

/**
 * PROJECT: mall
 * DESCRIPTION: auth user vo
 *
 * @author amos
 * @date 2019/7/26
 */
@Data
public class UserInfoVO implements Serializable {

    private static final long serialVersionUID = 8887971998850780679L;

    /**
     * 用户编号
     */
    private String userId;
    /**
     * 账号
     */
    private String account;
    /**
     * 用户名
     */
    private String username;

}
