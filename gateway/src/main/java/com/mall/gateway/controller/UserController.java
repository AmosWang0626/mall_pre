package com.mall.gateway.controller;

import com.mall.gateway.exception.ExceptionEnum;
import com.mall.gateway.exception.GenericResponse;
import com.mall.gateway.feign.UserFeignClient;
import com.mall.gateway.request.LoginRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class UserController extends BaseController {

    /**
     * 登录类型 - 注册
     */
    private static final String LOGIN_TYPE_REGISTER = "register";

    /**
     * 改用 feign
     * {@code RestTemplate restTemplate;}
     */
    @Resource
    private UserFeignClient userFeignClient;

    /*
     * 注册
     * {
     * 	"type": "register",
     * 	"phoneNo": "18937128861",
     * 	"email": "",
     * 	"password": "666666"
     * }
     * 登录
     * {
     * 	"phoneNo": "18937128861",
     * 	"email": "",
     * 	"password": "666666"
     * }
     */

    /**
     * 登录注册接口
     *
     * @param login         表单
     * @param bindingResult 表单验证
     * @return 通用状态
     */
    @PostMapping("login")
    public GenericResponse login(@Valid @RequestBody LoginRequest login, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new GenericResponse<>(ExceptionEnum.ERROR_PARAM_FORMAT).buildParam(bindingResult.getAllErrors());
        }
        if (StringUtils.isAllBlank(login.getPhoneNo(), login.getEmail())) {
            return new GenericResponse<>(ExceptionEnum.REGISTER_PHONE_EMAIL_BOTH_NULL);
        }
        /*ResponseEntity<String> responseEntity;*/
        if (login.getType() != null && LOGIN_TYPE_REGISTER.equalsIgnoreCase(login.getType())) {
            /*responseEntity = restTemplate.postForEntity("http://mall-user/user/register/", login, String.class);*/
            return userFeignClient.register(login);
        } else {
            /*responseEntity = restTemplate.postForEntity("http://mall-user/user/login/", login, String.class);*/
            return userFeignClient.login(login);
        }

        /*return genericResponse(responseEntity);*/
    }

}
