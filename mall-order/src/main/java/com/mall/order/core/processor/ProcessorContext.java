package com.mall.order.core.processor;

import com.mall.order.common.OrderStatusEnum;

/**
 * 模块名称: mall
 * 模块描述: 订单流转
 *
 * @author amos.wang
 * @date 2020/8/21 22:58
 */
public class ProcessorContext {

    private BaseProcessor baseProcessor;

    private OrderStatusEnum orderStatus;

    public ProcessorContext(BaseProcessor baseProcessor, OrderStatusEnum orderStatus) {
        this.baseProcessor = baseProcessor;
        this.orderStatus = orderStatus;
    }

    /**
     * 订单状态流转
     */
    public void next() {
        baseProcessor.next(this);
    }

    /**
     * 订单取消
     */
    public void cancel() {
        baseProcessor.cancel(this);
    }

    public BaseProcessor getBaseProcessor() {
        return baseProcessor;
    }

    public void setBaseProcessor(BaseProcessor baseProcessor) {
        this.baseProcessor = baseProcessor;
    }

    public OrderStatusEnum getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatusEnum orderStatus) {
        this.orderStatus = orderStatus;
    }
}
