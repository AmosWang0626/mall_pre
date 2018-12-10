package com.mall.user.request;

import lombok.Data;

import java.io.Serializable;

/**
 * PROJECT: user
 * DESCRIPTION: note
 *
 * @author Daoyuan
 * @date 2018/12/10
 */
@Data
public class RegisterRequest implements Serializable {

    private static final long serialVersionUID = -4580285546486328050L;

    /**
     * 昵称
     */
    private String nickName;
    /**
     * 手机号
     */
    private String phoneNo;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 密码
     */
    private String password;

}
