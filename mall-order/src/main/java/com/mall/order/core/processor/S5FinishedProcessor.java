package com.mall.order.core.processor;

/**
 * 模块名称: mall
 * 模块描述: 已完成订单
 *
 * @author amos.wang
 * @date 2020/8/21 22:48
 */
public class S5FinishedProcessor extends BaseProcessor {

    @Override
    public void next(ProcessorContext processorContext) {
        System.out.println("订单已完成，如有问题请联系售后客服");
    }
}
