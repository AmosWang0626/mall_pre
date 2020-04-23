package com.mall.order.web.controller;

import com.mall.common.base.GenericResponse;
import com.mall.order.core.service.OrderService;
import com.mall.order.pojo.form.OrderForm;
import com.mall.order.pojo.vo.OrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * PROJECT: order
 * DESCRIPTION: note
 *
 * @author Daoyuan
 * @date 2018/12/7
 */
@RestController
@RequestMapping("order")
@Api(tags = "A01-订单相关")
public class OrderController {

    @Resource
    private OrderService orderService;


    @PostMapping("preview")
    @ApiOperation("预览订单")
    public GenericResponse<OrderVO> preview(@RequestBody OrderForm form) {
        return orderService.preview(form);
    }

    @PostMapping("create")
    @ApiOperation("创建订单")
    public GenericResponse<OrderVO> create(@RequestBody OrderForm form) {
        return orderService.create(form);
    }

    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "orderNo", value = "订单编号", required = true,
                    dataType = "String", paramType = "query", example = "ODER20200423164213f530f8c42fc55e")
    })
    @GetMapping
    @ApiOperation("根据订单编号获取订单")
    public GenericResponse<OrderVO> get(String orderNo) {
        return new GenericResponse<>(orderService.getByOrderNo(orderNo));
    }

    @GetMapping("list")
    @ApiOperation("获取全部订单")
    public GenericResponse<List<OrderVO>> list() {
        return new GenericResponse<>(orderService.list(null));
    }

    @DeleteMapping
    @ApiOperation("根据订单编号删除订单")
    public GenericResponse<String> delete(String orderNo) {
        return orderService.delete(orderNo);
    }

    @DeleteMapping("real")
    @ApiOperation("根据订单编号删除订单（真删）")
    public GenericResponse<String> realDelete(String orderNo) {
        return orderService.realDelete(orderNo);
    }

}
