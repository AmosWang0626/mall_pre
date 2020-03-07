package com.mall.user.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * PROJECT: user
 * DESCRIPTION: note
 *
 * @author Daoyuan
 * @date 2018/12/7
 */
@RestController
@RequestMapping("integral")
public class IntegralController {

    @Value("${server.port}")
    private Integer port;

    @GetMapping("test")
    public String test() {
        return "用户积分处理完成!";
    }

    @GetMapping("port")
    public String port() {
        return "当前服务端口: " + port;
    }

}
