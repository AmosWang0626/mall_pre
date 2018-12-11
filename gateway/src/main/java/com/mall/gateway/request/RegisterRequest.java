package com.mall.gateway.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
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

    private static final long serialVersionUID = 8476359258603689996L;

    /**
     * 昵称
     */
    @NotBlank(message = "昵称不能为空")
    private String nickName;
    /**
     * 手机号
     */
    private String phoneNo;
    /**
     * 邮箱
     */
    @Email(message = "邮箱格式不对")
    private String email;
    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;

}
