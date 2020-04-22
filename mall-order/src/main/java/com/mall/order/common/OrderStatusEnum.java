package com.mall.order.common;

/**
 * PROJECT: order
 * DESCRIPTION: note
 *
 * @author Daoyuan
 * @date 2018/12/26
 */
public enum OrderStatusEnum {

    /**
     * 订单状态
     */
    AWAIT_PAY("待支付"),
    PAY_SUCCESS("付款"),
    PAY_CANCEL("取消订单"),
    INFO_CONFIRM("确认信息(多件商品)"),
    AWAIT_SHIP("待发货"),
    AWAIT_SHIP_CANCEL("待发货退款"),
    SHIPPED("待收货"),
    RECEIVED("确认收货"),
    SHIPPED_CANCEL("待收货退款"),
    FINISH("交易完成"),
    EVALUATION("评价"),
    AFTER_SALE("申请售后"),
    DONE("交易终态");

    private final String value;

    OrderStatusEnum(String value) {
        this.value = value;
    }

    private String getKey() {
        return this.name();
    }

    private String getValue() {
        return this.value;
    }
}
