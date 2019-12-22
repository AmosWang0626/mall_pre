package com.mall.user.web.controller;

import com.mall.common.api.CommonController;
import com.mall.common.response.GenericResponse;
import com.mall.user.business.UserBusiness;
import com.mall.user.common.pojo.request.LoginRequest;
import com.mall.user.common.pojo.response.AuthUserVO;
import com.mall.user.common.pojo.response.UserInfoVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * PROJECT: user
 * DESCRIPTION: note
 *
 * @author Daoyuan
 * @date 2018/12/7
 */
@RestController
@RequestMapping("user")
public class UserController implements CommonController {

    @Resource
    private UserBusiness userBusiness;

    @GetMapping("test")
    public String test() {
        return "用户系统处理成功!";
    }

    @PostMapping("register")
    public GenericResponse<AuthUserVO> register(@RequestBody LoginRequest register) {
        return userBusiness.register(register);
    }

    @PostMapping("login")
    public GenericResponse<AuthUserVO> login(@RequestBody LoginRequest login) {
        return userBusiness.login(login);
    }

    @PostMapping("authLoginInfo")
    public GenericResponse<AuthUserVO> authLoginInfo(@RequestBody LoginRequest login) {
        return userBusiness.authLoginInfo(login);
    }

    @GetMapping("getUserInfo")
    public GenericResponse<UserInfoVO> getUserInfo(HttpServletRequest request) {
        return userBusiness.getUserInfo(getUserId(request));
    }

}
