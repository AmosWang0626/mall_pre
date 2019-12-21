package com.mall.user.web.controller;

import com.mall.common.response.GenericResponse;
import com.mall.user.business.UserBusiness;
import com.mall.user.common.pojo.request.LoginRequest;
import com.mall.user.common.pojo.response.AuthUserVO;
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
public class UserController {

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

}
