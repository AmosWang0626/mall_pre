package com.mall.user.business;

import com.mall.common.response.GenericResponse;
import com.mall.user.common.pojo.request.LoginRequest;
import com.mall.user.common.pojo.response.AuthUserVO;
import com.mall.user.common.pojo.response.UserInfoVO;

/**
 * PROJECT: user
 * DESCRIPTION: note
 *
 * @author Daoyuan
 * @date 2018/12/11
 */
public interface UserBusiness {

    /**
     * 注册
     *
     * @param loginRequest 注册表单
     * @return AuthUserVO
     */
    GenericResponse<AuthUserVO> register(LoginRequest loginRequest);

    /**
     * 登录
     *
     * @param loginRequest 登录表单
     * @return AuthUserVO
     */
    GenericResponse<AuthUserVO> login(LoginRequest loginRequest);

    /**
     * 获取登录鉴权信息
     *
     * @param loginRequest 登录表单
     * @return AuthUserVO
     */
    GenericResponse<AuthUserVO> authLoginInfo(LoginRequest loginRequest);

    /**
     * 获取用户信息
     *
     * @param userId 用户ID
     * @return UserEntity
     */
    GenericResponse<UserInfoVO> getUserInfo(String userId);

}
