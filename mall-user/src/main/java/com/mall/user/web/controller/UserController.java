package com.mall.user.web.controller;

import com.mall.common.api.Token;
import com.mall.common.auth.CurrentUserContext;
import com.mall.common.base.GenericResponse;
import com.mall.common.pojo.response.AuthUserVO;
import com.mall.user.api.pojo.request.LoginRequest;
import com.mall.user.business.UserBusiness;
import com.mall.user.common.pojo.response.UserInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * PROJECT: user
 * DESCRIPTION: note
 *
 * @author Daoyuan
 * @date 2018/12/7
 */
@RestController
@RequestMapping("user")
@Api(tags = "A01-用户相关")
public class UserController {

    @Resource
    private UserBusiness userBusiness;


    @Token(check = false)
    @PostMapping("register")
    @ApiOperation("用户注册")
    public GenericResponse<AuthUserVO> register(@RequestBody LoginRequest register) {
        return userBusiness.register(register);
    }

    @Token(check = false)
    @PostMapping("login")
    @ApiOperation("用户登录")
    public GenericResponse<AuthUserVO> login(@RequestBody LoginRequest login) {
        return userBusiness.login(login);
    }


    @GetMapping("getUserInfo")
    @ApiOperation("获取用户信息")
    public GenericResponse<UserInfoVO> getUserInfo() {
        return userBusiness.getUserInfo(CurrentUserContext.getAuthUser().getUserId());
    }

}
