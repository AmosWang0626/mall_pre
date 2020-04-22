package com.mall.order.pojo.dto;

import com.mall.order.dao.entity.OrderDetailEntity;
import com.mall.order.dao.entity.OrderEntity;
import lombok.Data;

import java.util.List;

/**
 * 模块名称: mall
 * 模块描述: 订单初始化 DTO
 *
 * @author amos.wang
 * @date 2020/4/22 18:43
 */
@Data
public class OrderInitDTO {

    private OrderEntity orderEntity;

    private List<OrderDetailEntity> orderDetailEntities;

}
