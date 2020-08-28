package com.mall.user.business;

import com.mall.common.base.GenericResponse;
import com.mall.user.api.service.IUserLoginService;
import com.mall.user.common.pojo.response.UserInfoVO;

/**
 * PROJECT: user
 * DESCRIPTION: note
 *
 * @author Daoyuan
 * @date 2018/12/11
 */
public interface UserBusiness extends IUserLoginService {

    /**
     * 获取用户信息
     *
     * @param userId 用户ID
     * @return UserEntity
     */
    GenericResponse<UserInfoVO> getUserInfo(String userId);

}
