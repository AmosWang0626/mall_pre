package com.mall.order;

import com.mall.order.common.OrderStatusEnum;
import com.mall.order.core.processor.ProcessorContext;
import com.mall.order.core.processor.S0CreatedProcessor;

/**
 * 模块名称: mall
 * 模块描述: 订单流转测试
 *
 * @author amos.wang
 * @date 2020/8/21 23:53
 */
public class OrderStatusProcessorTests {

    public static void main(String[] args) {
        ProcessorContext context = new ProcessorContext(new S0CreatedProcessor(), OrderStatusEnum.CREATED);
        context.next();
        context.next();
        context.next();
        context.next();
        context.next();

        context = new ProcessorContext(new S0CreatedProcessor(), OrderStatusEnum.CREATED);
        context.next();
        context.next();
        context.cancel();
        context.cancel();
        context.cancel();
        context.cancel();
        context.cancel();
    }

}
