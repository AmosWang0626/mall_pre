package com.mall.warehouse.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * PROJECT: warehouse
 * DESCRIPTION: note
 *
 * @author Daoyuan
 * @date 2018/12/7
 */
@RestController
@RequestMapping("warehouse")
public class WarehouseController {

    @GetMapping("test")
    public String test() {
        return "仓储系统处理成功!";
    }

}
