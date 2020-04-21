package com.mall.order.web.controller;

import com.mall.common.base.GenericResponse;
import com.mall.order.pojo.vo.OrderVO;
import com.mall.order.core.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * PROJECT: order
 * DESCRIPTION: note
 *
 * @author Daoyuan
 * @date 2018/12/7
 */
@RestController
@RequestMapping("order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("test")
    public String test() {
        return "订单系统处理成功!";
    }

    @GetMapping
    public GenericResponse<OrderVO> getOrder(String orderId) {
        return orderService.findById(orderId);
    }

}
