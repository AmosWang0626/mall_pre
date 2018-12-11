package com.mall.user.controller;

import com.mall.user.dao.entity.UserEntity;
import com.mall.user.dao.mapper.UserMapper;
import com.mall.user.request.LoginRequest;
import com.mall.user.request.RegisterRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * PROJECT: user
 * DESCRIPTION: note
 *
 * @author Daoyuan
 * @date 2018/12/7
 */
@RestController
@RequestMapping("user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserMapper userMapper;

    @GetMapping("test")
    public String test() {
        return "用户系统处理成功!";
    }

    @PostMapping("register")
    public String register(@RequestBody RegisterRequest register) {

        return "";
    }

    @PostMapping("login")
    public String login(@RequestBody LoginRequest login) {

        return "";
    }

}
