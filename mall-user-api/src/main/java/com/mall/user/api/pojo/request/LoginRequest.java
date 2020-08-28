package com.mall.user.api.pojo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

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
@Accessors(chain = true)
public class LoginRequest implements Serializable {

    private static final long serialVersionUID = -8562669854109829185L;

    @NotBlank(message = "账号不能为空")
    @ApiModelProperty(value = "账号", required = true, example = "amos")
    private String account;

    @ApiModelProperty(value = "用户名", example = "amos")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 16, message = "密码长度 6 ~ 16")
    @ApiModelProperty(value = "密码", required = true, example = "123456")
    private String password;

}
