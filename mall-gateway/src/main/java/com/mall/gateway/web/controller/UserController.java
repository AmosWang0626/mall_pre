package com.mall.gateway.web.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.mall.common.base.GenericResponse;
import com.mall.common.pojo.response.AuthUserVO;
import com.mall.gateway.common.BaseController;
import com.mall.gateway.common.pojo.request.LoginRequest;
import com.mall.gateway.common.pojo.response.LoginInfoVO;
import com.mall.gateway.common.utils.JwtUtils;
import com.mall.gateway.feign.UserFeignClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

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
@Api(tags = "用户相关")
public class UserController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserFeignClient userFeignClient;


    /**
     * 注册接口
     *
     * @param login 表单
     * @return Mono
     */
    @PostMapping("register")
    @SentinelResource("register")
    @ApiOperation(value = "注册接口")
    public Mono<GenericResponse<LoginInfoVO>> register(@Valid @RequestBody Mono<LoginRequest> login) {
        return login.map(loginRequest -> formatLoginInfo(userFeignClient.register(loginRequest)))
                .onErrorResume(fallback());
    }

    /**
     * 登录接口
     *
     * @param login 表单
     * @return Mono
     */
    @PostMapping(value = "login")
    @SentinelResource("login")
    @ApiOperation(value = "登录接口")
    public Mono<GenericResponse<LoginInfoVO>> login(@Valid @RequestBody Mono<LoginRequest> login) {
        return login.map(loginRequest -> formatLoginInfo(userFeignClient.login(loginRequest)))
                .onErrorResume(fallback());
    }


    /**
     * 生成登录相关信息
     */
    private GenericResponse<LoginInfoVO> formatLoginInfo(GenericResponse<AuthUserVO> response) {
        if (response.unSuccessful()) {
            return GenericResponse.format(response);
        }
        AuthUserVO authUserVO = response.getBody();

        LoginInfoVO loginInfoVO = new LoginInfoVO();
        loginInfoVO.setUserId(authUserVO.getUserId());
        loginInfoVO.setAccount(authUserVO.getAccount());
        loginInfoVO.setUsername(authUserVO.getUsername());
        // 登录成功, 设置Token, 设置登录标识(用于单设备登录)
        loginInfoVO.setToken(JwtUtils.sign(authUserVO, UUID.randomUUID().toString()));
        LOGGER.info("用户 [{}] 登录成功", authUserVO.getUsername());
        return new GenericResponse<>(loginInfoVO);
    }

}
