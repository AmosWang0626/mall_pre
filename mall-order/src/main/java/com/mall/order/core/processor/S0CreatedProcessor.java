package com.mall.order.core.processor;

import com.mall.order.common.OrderStatusEnum;

/**
 * 模块名称: mall
 * 模块描述: 已创建订单
 *
 * @author amos.wang
 * @date 2020/8/21 22:48
 */
public class S0CreatedProcessor extends BaseProcessor {

    @Override
    public void next(ProcessorContext processorContext) {
        if (OrderStatusEnum.CREATED.equals(processorContext.getOrderStatus())) {
            processorContext.setOrderStatus(OrderStatusEnum.PAID);
            processorContext.setBaseProcessor(new S1PaidProcessor());
            System.out.println("订单已支付");
        } else {
            System.out.println("订单已支付，请勿重复支付");
        }
    }

    @Override
    public void cancel(ProcessorContext processorContext) {
        processorContext.setOrderStatus(OrderStatusEnum.CANCELED);
        processorContext.setBaseProcessor(new S5FinishedProcessor());
        System.out.println("待付款订单已取消");
    }
}
