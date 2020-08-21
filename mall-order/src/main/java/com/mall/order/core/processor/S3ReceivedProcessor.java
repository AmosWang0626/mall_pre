package com.mall.order.core.processor;

import com.mall.order.common.OrderStatusEnum;

/**
 * 模块名称: mall
 * 模块描述: 已签收订单
 *
 * @author amos.wang
 * @date 2020/8/21 22:48
 */
public class S3ReceivedProcessor extends BaseProcessor {

    @Override
    public void next(ProcessorContext processorContext) {
        if (OrderStatusEnum.RECEIVED.equals(processorContext.getOrderStatus())) {
            processorContext.setOrderStatus(OrderStatusEnum.FINISHED);
            processorContext.setBaseProcessor(new S5FinishedProcessor());
            System.out.println("订单已完成");
        } else {
            System.out.println("订单已完成，如有问题请申请售后");
        }
    }

    @Override
    public void cancel(ProcessorContext processorContext) {
        processorContext.setOrderStatus(OrderStatusEnum.AFTER_SALE);
        processorContext.setBaseProcessor(new S4AfterSaleProcessor());
        System.out.println("已签收订单请申请售后");
    }
}
