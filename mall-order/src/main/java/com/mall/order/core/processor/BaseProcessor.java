package com.mall.order.core.processor;

/**
 * 模块名称: mall
 * 模块描述: 订单流转
 *
 * @author amos.wang
 * @date 2020/8/21 22:46
 */
public abstract class BaseProcessor {

    /**
     * 订单流转
     *
     * @param processorContext order
     */
    public abstract void next(ProcessorContext processorContext);

    /**
     * 取消订单
     */
    public void cancel(ProcessorContext processorContext) {
        System.out.println("订单已完成，如有问题请联系售后客服");
    }

}
