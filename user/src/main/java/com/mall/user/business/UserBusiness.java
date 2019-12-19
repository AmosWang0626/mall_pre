package com.mall.user.business;

import com.mall.common.response.GenericResponse;
import com.mall.user.common.pojo.request.LoginRequest;

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
     * @return UserEntity
     */
    GenericResponse register(LoginRequest loginRequest);

    /**
     * 登录
     *
     * @param loginRequest 登录表单
     * @return UserEntity
     */
    GenericResponse login(LoginRequest loginRequest);

}
