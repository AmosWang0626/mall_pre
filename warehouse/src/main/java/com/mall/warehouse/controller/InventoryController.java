package com.mall.warehouse.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * PROJECT: warehouse
 * DESCRIPTION: note
 *
 * @author Daoyuan
 * @date 2018/12/9
 */
@RestController
@RequestMapping("inventory")
public class InventoryController {

    @GetMapping("test")
    public String test() {
        return "库存系统处理成功!";
    }

}