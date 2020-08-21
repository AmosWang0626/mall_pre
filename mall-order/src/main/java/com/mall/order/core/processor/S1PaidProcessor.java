package com.mall.order.core.processor;

import com.mall.order.common.OrderStatusEnum;

/**
 * 模块名称: mall
 * 模块描述: 已支付订单
 *
 * @author amos.wang
 * @date 2020/8/21 22:48
 */
public class S1PaidProcessor extends BaseProcessor {

    @Override
    public void next(ProcessorContext processorContext) {
        if (OrderStatusEnum.PAID.equals(processorContext.getOrderStatus())) {
            processorContext.setOrderStatus(OrderStatusEnum.SHIPPED);
            processorContext.setBaseProcessor(new S2ShippedProcessor());
            System.out.println("订单已发货");
        } else {
            System.out.println("订单已发货，请查看物流信息");
        }
    }

    @Override
    public void cancel(ProcessorContext processorContext) {
        processorContext.setOrderStatus(OrderStatusEnum.AFTER_SALE);
        processorContext.setBaseProcessor(new S4AfterSaleProcessor());
        System.out.println("已支付订单请申请售后");
    }
}
