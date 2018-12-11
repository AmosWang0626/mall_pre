package com.mall.gateway.controller;

import com.mall.gateway.exception.ExceptionEnum;
import com.mall.gateway.exception.GenericResponse;
import com.mall.gateway.request.LoginRequest;
import com.mall.gateway.request.RegisterRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * PROJECT: gateway
 * DESCRIPTION: note
 *
 * @author Daoyuan
 * @date 2018/12/11
 */
@RestController
@RequestMapping("user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Resource
    private RestTemplate restTemplate;

    @PostMapping("register")
    public GenericResponse register(@Valid @RequestBody RegisterRequest register, BindingResult bindingResult) {
        // 参数校验
        if (bindingResult.hasErrors()) {
            return new GenericResponse<>(ExceptionEnum.ERROR_PARAM_FORMAT).setParam(bindingResult.getAllErrors());
        }
        if (StringUtils.isAnyBlank(register.getPhoneNo(), register.getEmail())) {

        }


        return GenericResponse.SUCCESS;
    }

    @PostMapping("login")
    public GenericResponse login(@Valid @RequestBody LoginRequest login, BindingResult bindingResult) {
        // 参数校验
        if (bindingResult.hasErrors()) {
            return new GenericResponse<>(ExceptionEnum.ERROR_PARAM_FORMAT).setParam(bindingResult.getAllErrors());
        }


        return GenericResponse.SUCCESS;
    }

}
