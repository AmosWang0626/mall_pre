package com.mall.order.core.processor;

import com.mall.order.common.OrderStatusEnum;

/**
 * 模块名称: mall
 * 模块描述: 已发货订单
 *
 * @author amos.wang
 * @date 2020/8/21 22:48
 */
public class S2ShippedProcessor extends BaseProcessor {

    @Override
    public void next(ProcessorContext processorContext) {
        if (OrderStatusEnum.SHIPPED.equals(processorContext.getOrderStatus())) {
            processorContext.setOrderStatus(OrderStatusEnum.RECEIVED);
            processorContext.setBaseProcessor(new S3ReceivedProcessor());
            System.out.println("订单已签收");
        } else {
            System.out.println("订单已签收，记得评价哟");
        }
    }

    @Override
    public void cancel(ProcessorContext processorContext) {
        processorContext.setOrderStatus(OrderStatusEnum.AFTER_SALE);
        processorContext.setBaseProcessor(new S4AfterSaleProcessor());
        System.out.println("已发货订单请申请售后");
    }
}
