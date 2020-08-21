package com.mall.order.core.processor;

import com.mall.order.common.OrderStatusEnum;

/**
 * 模块名称: mall
 * 模块描述: 售后订单
 *
 * @author amos.wang
 * @date 2020/8/21 22:48
 */
public class S4AfterSaleProcessor extends BaseProcessor {

    @Override
    public void next(ProcessorContext processorContext) {
        if (OrderStatusEnum.AFTER_SALE.equals(processorContext.getOrderStatus())) {
            processorContext.setOrderStatus(OrderStatusEnum.REFUNDED);
            processorContext.setBaseProcessor(new S5FinishedProcessor());
            System.out.println("已退款，交易关闭");
        } else {
            System.out.println("已退款，如有问题请联系售后客服");
        }
    }
}
