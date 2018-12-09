package com.mall.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * PROJECT: gateway
 * DESCRIPTION: note
 *
 * @author Daoyuan
 * @date 2018/12/7
 */
@RestController
@RequestMapping("test")
public class TestController {

    @Resource
    private RestTemplate restTemplate;

    /**
     * 用户系统
     */
    @GetMapping("user")
    public String user() {
        return restTemplate.getForEntity("http://mall-user/user/test/", String.class).getBody();
    }

    /**
     * 积分系统
     */
    @GetMapping("integral")
    public String integral() {
        return restTemplate.getForEntity("http://mall-user/integral/test/", String.class).getBody();
    }

    /**
     * 订单系统
     */
    @GetMapping("order")
    public String order() {
        return restTemplate.getForEntity("http://mall-order/order/test/", String.class).getBody();
    }

    /**
     * 库存系统
     */
    @GetMapping("inventory")
    public String inventory() {
        return restTemplate.getForEntity("http://mall-warehouse/inventory/test/", String.class).getBody();
    }

    /**
     * 仓储系统
     */
    @GetMapping("warehouse")
    public String warehouse() {
        return restTemplate.getForEntity("http://mall-warehouse/warehouse/test/", String.class).getBody();
    }

}
