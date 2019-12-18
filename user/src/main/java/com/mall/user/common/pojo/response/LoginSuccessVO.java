package com.mall.user.common.pojo.response;

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
    private String account;
    private String username;

}
