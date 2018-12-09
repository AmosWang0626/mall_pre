package com.mall.user.controller;

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

    @GetMapping("test")
    public String test() {
        return "用户积分处理完成!";
    }

}
