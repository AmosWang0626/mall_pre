package com.mall.order.common;

/**
 * PROJECT: order
 * DESCRIPTION: 订单状态
 *
 * @author Daoyuan
 * @date 2018/12/26
 */
public enum OrderStatusEnum {

    /***/
    CREATED("待付款", "创建订单"),
    PAID("待发货", "已付款"),
    SHIPPED("待收货", "已发货"),
    RECEIVED("评价", "已收货"),
    AFTER_SALE("退款/售后", "退款/售后"),
    REFUNDED("已退款", "已退款"),
    CANCELED("已取消", "已取消"),
    FINISHED("已完成", "已完成");

    /**
     * 用户状态
     */
    private final String value;
    /**
     * 后台订单状态
     */
    private final String description;


    OrderStatusEnum(String value, String description) {
        this.value = value;
        this.description = description;
    }

    private String getKey() {
        return this.name();
    }

    private String getValue() {
        return this.value;
    }

    public String getDescription() {
        return description;
    }
}
