package com.mall.user.api.service;

import com.mall.common.base.GenericResponse;
import com.mall.common.pojo.response.AuthUserVO;
import com.mall.user.api.pojo.request.LoginRequest;

/**
 * PROJECT: gateway
 * DESCRIPTION: note
 *
 * @author Daoyuan
 * @date 2018/12/21
 */
public interface IUserLoginService {

    /**
     * 注册
     *
     * @param register form
     * @return GenericResponse
     */
    GenericResponse<AuthUserVO> register(LoginRequest register);

    /**
     * 登录
     *
     * @param login form
     * @return GenericResponse
     */
    GenericResponse<AuthUserVO> login(LoginRequest login);

    /**
     * 根据账号获取用户信息
     *
     * @param login form
     * @return GenericResponse
     */
    GenericResponse<AuthUserVO> authLoginInfo(LoginRequest login);

}
