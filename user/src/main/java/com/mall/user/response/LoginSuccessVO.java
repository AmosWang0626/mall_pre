package com.mall.user.response;

import lombok.Data;

/**
 * PROJECT: user
 * DESCRIPTION: note
 *
 * @author Daoyuan
 * @date 2018/12/20
 */
@Data
public class LoginSuccessVO {

    private String token;
    private String nickName;
    private String phoneNo;
    private String email;
    private String name;
    private Integer gender;
    private String identityNo;
}
