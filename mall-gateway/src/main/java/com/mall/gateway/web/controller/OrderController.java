package com.mall.gateway.web.controller;

import com.mall.gateway.common.BaseController;
import com.mall.gateway.common.request.LogisticsRequest;
import com.mall.gateway.config.GatewayConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * PROJECT: gateway
 * DESCRIPTION: note
 *
 * @author Daoyuan
 * @date 2018/12/7
 */
@Slf4j
@RestController
@RequestMapping("order")
public class OrderController extends BaseController {

    /**
     * 创建订单
     */
    @GetMapping("create")
    public String create() {
        return null;
    }

    /**
     * 查看物流
     */
    @GetMapping("logistics/{type}/{value}")
    public String logistics(@PathVariable("type") String type, @PathVariable("value") String value) {
        if (StringUtils.isAnyBlank(type, value)) {
            return "参数错误!";
        }
        LogisticsRequest logisticsRequest = new LogisticsRequest();
        if (GatewayConstant.USER.equalsIgnoreCase(type)) {
            logisticsRequest.setUserId(value);
        } else if (GatewayConstant.PHONE.equalsIgnoreCase(type)) {
            logisticsRequest.setPhoneNo(value);
        } else if (GatewayConstant.LOG.equalsIgnoreCase(type)) {
            logisticsRequest.setLogisticsNo(value);
        } else if (GatewayConstant.ORDER.equalsIgnoreCase(type)) {
            logisticsRequest.setOrderNo(value);
        } else {
            return "参数非法!";
        }

        return restTemplate.postForEntity("http://mall-order/logistics/query", logisticsRequest, String.class).getBody();
    }

}
