package com.mall.warehouse.controller;

import com.mall.warehouse.core.service.InventoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;

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

    @Resource
    private InventoryService inventoryService;

    @GetMapping("consume")
    public String consume() {
        String response = inventoryService.consume(UUID.randomUUID().toString());
        System.out.println(response);

        return response;
    }

}