package com.mall.gateway.feign;

import com.mall.common.response.GenericResponse;
import com.mall.gateway.common.pojo.request.LoginRequest;
import com.mall.gateway.common.pojo.dto.AuthUserVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * PROJECT: gateway
 * DESCRIPTION: note
 *
 * @author Daoyuan
 * @date 2018/12/21
 */
@FeignClient(name = "mall-user", fallbackFactory = UserFeignFallbackFactory.class)
public interface UserFeignClient {

    /**
     * 注册
     *
     * @param register form
     * @return GenericResponse
     */
    @PostMapping("/user/register")
    GenericResponse<AuthUserVO> register(@RequestBody LoginRequest register);

    /**
     * 登录
     *
     * @param login form
     * @return GenericResponse
     */
    @PostMapping("/user/login")
    GenericResponse<AuthUserVO> login(@RequestBody LoginRequest login);

    /**
     * 根据账号获取用户信息
     *
     * @param login form
     * @return GenericResponse
     */
    @PostMapping("/user/authLoginInfo")
    GenericResponse<AuthUserVO> authLoginInfo(@RequestBody LoginRequest login);

}
