package com.mall.gateway.web.controller;

import com.mall.common.response.GenericResponse;
import com.mall.gateway.common.BaseController;
import com.mall.gateway.common.exception.GatewayExceptionEnum;
import com.mall.gateway.common.pojo.dto.AuthUserVO;
import com.mall.gateway.common.pojo.request.LoginRequest;
import com.mall.gateway.common.pojo.response.LoginInfoVO;
import com.mall.gateway.common.utils.JwtUtils;
import com.mall.gateway.feign.UserFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.UUID;

/**
 * PROJECT: gateway
 * DESCRIPTION: note
 *
 * @author Daoyuan
 * @date 2018/12/11
 */
@RestController
@RequestMapping("user")
public class UserController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserFeignClient userFeignClient;


    /**
     * 注册接口
     *
     * @param login         表单
     * @param bindingResult 表单验证
     * @return GenericResponse
     */
    @PostMapping("register")
    public GenericResponse register(@Valid @RequestBody LoginRequest login, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new GenericResponse<>(GatewayExceptionEnum.ERROR_PARAM_FORMAT, bindingResult.getAllErrors());
        }

        return generateLoginResponse(userFeignClient.register(login));
    }

    /**
     * 登录接口
     *
     * @param login         表单
     * @param bindingResult 表单验证
     * @return GenericResponse
     */
    @PostMapping("login")
    public GenericResponse login(@Valid @RequestBody LoginRequest login, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new GenericResponse<>(GatewayExceptionEnum.ERROR_PARAM_FORMAT, bindingResult.getAllErrors());
        }

        return generateLoginResponse(userFeignClient.login(login));
    }


    /**
     * 生成登录相关信息
     */
    private GenericResponse generateLoginResponse(GenericResponse<AuthUserVO> response) {
        if (response.unSuccessful()) {
            return response;
        }
        AuthUserVO authUserVO = response.getBody();

        LoginInfoVO loginInfoVO = new LoginInfoVO();
        loginInfoVO.setUserId(authUserVO.getUserId());
        loginInfoVO.setAccount(authUserVO.getAccount());
        loginInfoVO.setUsername(authUserVO.getUsername());
        loginInfoVO.setToken(JwtUtils.sign(authUserVO, UUID.randomUUID().toString()));

        return new GenericResponse<>(loginInfoVO);
    }

}
