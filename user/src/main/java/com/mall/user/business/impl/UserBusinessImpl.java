package com.mall.user.business.impl;

import com.mall.common.response.GenericResponse;
import com.mall.user.business.UserBusiness;
import com.mall.user.common.UserExceptionEnum;
import com.mall.user.constant.UserConstant;
import com.mall.user.dao.entity.UserEntity;
import com.mall.user.dao.mapper.UserMapper;
import com.mall.user.request.LoginRequest;
import com.mall.user.response.LoginSuccessVO;
import com.mall.user.util.CheckUtil;
import com.mall.user.util.DesSecretUtil;
import com.mall.user.util.RandomUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * PROJECT: user
 * DESCRIPTION: note
 *
 * @author Daoyuan
 * @date 2018/12/11
 */
@Component("userBusiness")
public class UserBusinessImpl implements UserBusiness {

    @Resource
    private UserMapper userMapper;

    @Override
    public GenericResponse register(LoginRequest loginRequest) {
        if (!CheckUtil.isPhoneNo(loginRequest.getPhoneNo())) {
            return new GenericResponse(UserExceptionEnum.PHONE_NO_FORMAT_ERROR);
        }
        UserEntity userEntity = getUserEntity(loginRequest);

        if (userEntity != null) {
            return new GenericResponse(UserExceptionEnum.REGISTER_ALREADY_PHONE_EMAIL);
        }

        userEntity = new UserEntity();
        BeanUtils.copyProperties(loginRequest, userEntity);
        String pwd = loginRequest.getPassword();
        String salt = RandomUtil.generateString(8);
        String encryptPwd = DesSecretUtil.encrypt(pwd, salt);
        userEntity.setSalt(salt);
        userEntity.setPassword(encryptPwd);
        userEntity.setCreateTime(new Date());
        UserEntity entity = userMapper.save(userEntity);

        if (entity != null) {
            return new GenericResponse<>(generateLoginSuccessVO(entity));
        }

        return new GenericResponse(UserExceptionEnum.REGISTER_FAIL_DB_ERROR);
    }

    @Override
    public GenericResponse login(LoginRequest loginRequest) {
        if (!CheckUtil.isPhoneNo(loginRequest.getPhoneNo())) {
            return new GenericResponse(UserExceptionEnum.PHONE_NO_FORMAT_ERROR);
        }
        UserEntity userEntity = getUserEntity(loginRequest);

        if (userEntity == null) {
            return new GenericResponse(UserExceptionEnum.LOGIN_ACCOUNT_NOT_FOUND);
        }

        String salt = userEntity.getSalt();
        String encryptPwd = DesSecretUtil.encrypt(loginRequest.getPassword(), salt);
        if (encryptPwd.equals(userEntity.getPassword())) {
            return new GenericResponse<>(generateLoginSuccessVO(userEntity));
        }

        return new GenericResponse(UserExceptionEnum.LOGIN_PASSWORD_ERROR);
    }

    private LoginSuccessVO generateLoginSuccessVO(UserEntity userEntity) {
        LoginSuccessVO successVO = new LoginSuccessVO();
        BeanUtils.copyProperties(userEntity, successVO);
        if (userEntity.getGender() != null) {
            successVO.setGender(userEntity.getGender() ? 1 : 0);
        }
        successVO.setToken(DesSecretUtil.encrypt(userEntity.getId().toString(), UserConstant.TOKEN_SECRET));
        return successVO;
    }

    private UserEntity getUserEntity(LoginRequest loginRequest) {
        UserEntity userEntity = null;
        boolean phoneNotBlank = StringUtils.isNotBlank(loginRequest.getPhoneNo());
        boolean emailNotBlank = StringUtils.isNotBlank(loginRequest.getEmail());
        if (!phoneNotBlank) {
            loginRequest.setPhoneNo(null);
        }
        if (!emailNotBlank) {
            loginRequest.setEmail(null);
        }
        if (phoneNotBlank && emailNotBlank) {
            userEntity = userMapper.findByPhoneNoOrEmail(loginRequest.getPhoneNo(), loginRequest.getEmail());
        } else if (phoneNotBlank) {
            userEntity = userMapper.findByPhoneNo(loginRequest.getPhoneNo());
        } else if (emailNotBlank) {
            userEntity = userMapper.findByEmail(loginRequest.getEmail());
        }
        return userEntity;
    }

}
