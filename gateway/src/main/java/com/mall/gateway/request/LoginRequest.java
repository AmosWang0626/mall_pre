package com.mall.gateway.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * PROJECT: user
 * DESCRIPTION: note
 *
 * @author Daoyuan
 * @date 2018/12/10
 */
@Data
public class LoginRequest implements Serializable {

    private static final long serialVersionUID = -1539474854713930650L;

    /**
     * 登录 or 注册
     */
    @NotBlank(message = "类型不能为空")
    private String type;
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
    @Size(min = 6, max = 16, message = "密码长度 6 ~ 16")
    private String password;
}
