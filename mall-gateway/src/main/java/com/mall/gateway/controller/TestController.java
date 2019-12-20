package com.mall.gateway.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private LoadBalancerClient loadBalancerClient;

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

    @GetMapping("log-user")
    public void logUser() {
        ServiceInstance serviceInstance = loadBalancerClient.choose("mall-user");
        LOGGER.info("{}:{}:{}", serviceInstance.getServiceId(), serviceInstance.getHost(), serviceInstance.getPort());
    }

}
