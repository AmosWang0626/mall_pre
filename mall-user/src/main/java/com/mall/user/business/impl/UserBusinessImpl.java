package com.mall.user.business.impl;

import com.mall.common.base.GenericResponse;
import com.mall.common.pojo.response.AuthUserVO;
import com.mall.common.util.DesSecretUtil;
import com.mall.common.util.RandomUtil;
import com.mall.user.api.pojo.request.LoginRequest;
import com.mall.user.business.UserBusiness;
import com.mall.user.common.enums.UserExceptionEnum;
import com.mall.user.common.pojo.UserConverter;
import com.mall.user.common.pojo.response.UserInfoVO;
import com.mall.user.dao.entity.UserEntity;
import com.mall.user.dao.mapper.UserMapper;
import com.mall.user.web.exception.AccountErrorException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Optional;

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
    @Resource
    private UserConverter userConverter;

    @Override
    public GenericResponse<AuthUserVO> register(LoginRequest loginRequest) {
        Optional<UserEntity> userOptional = userMapper.findByAccount(loginRequest.getAccount());

        if (userOptional.isPresent()) {
            return new GenericResponse<>(UserExceptionEnum.REGISTER_ACCOUNT_EXISTED);
        }

        UserEntity userEntity = userConverter.convert(loginRequest);
        String pwd = loginRequest.getPassword();
        String salt = RandomUtil.generateString(8);
        String encryptPwd = DesSecretUtil.encrypt(pwd, salt);
        userEntity.setSalt(salt);
        userEntity.setStatus(1);
        userEntity.setPassword(encryptPwd);
        userEntity.setCreateTime(LocalDateTime.now());

        return new GenericResponse<>(userConverter.reduction(userMapper.save(userEntity)));
    }

    @Override
    public GenericResponse<AuthUserVO> login(LoginRequest loginRequest) {
        Optional<UserEntity> userOptional = userMapper.findByAccount(loginRequest.getAccount());

        if (!userOptional.isPresent()) {
            return new GenericResponse<>(UserExceptionEnum.LOGIN_ACCOUNT_NOT_FOUND);
        }
        UserEntity userEntity = userOptional.get();
        String encryptPwd = DesSecretUtil.encrypt(loginRequest.getPassword(), userEntity.getSalt());
        if (!userEntity.getPassword().equals(encryptPwd)) {
            return new GenericResponse<>(UserExceptionEnum.LOGIN_PASSWORD_ERROR);
        }

        return new GenericResponse<>(userConverter.reduction(userEntity));
    }

    @Override
    public GenericResponse<AuthUserVO> authLoginInfo(LoginRequest loginRequest) {
        return new GenericResponse<>(Optional.ofNullable(userMapper
                .findByAccount(loginRequest.getAccount())
                .orElseThrow(() -> new AccountErrorException(loginRequest.getAccount() + " 账号不存在"))
        ).map(userEntity -> userConverter.reduction(userEntity)).get());
    }

    @Override
    public GenericResponse<UserInfoVO> getUserInfo(String userId) {
        UserInfoVO userInfoVO = new UserInfoVO();
        userMapper.findById(userId)
                .ifPresent(userEntity -> userInfoVO.setUserId(userEntity.getId())
                        .setAccount(userEntity.getAccount()).setUsername(userEntity.getUsername()));

        return new GenericResponse<>(userInfoVO);
    }

}
