package com.mall.user.api.pojo.response;

import com.mall.common.pojo.response.BaseVO;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * PROJECT: mall
 * DESCRIPTION: auth user vo
 *
 * @author amos
 * @date 2019/7/26
 */
@Accessors(chain = true)
@Getter
@Setter
public class AuthUserVO extends BaseVO implements Serializable {

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

    /**
     * 密码盐
     */
    private String salt;
    /**
     * 密码
     */
    private String password;

}
