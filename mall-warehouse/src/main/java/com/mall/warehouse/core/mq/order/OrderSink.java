package com.mall.warehouse.core.mq.order;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * 模块名称: mall-user
 * 模块描述: 自定义消息通道
 *
 * @author amos.wang
 * @date 2020/4/9 21:23
 */
public interface OrderSink {

    /**
     * 接收消息的通道名称
     */
    String ORDER = "order";


    /**
     * 接收消息通道
     *
     * @return SubscribableChannel
     */
    @Input(OrderSink.ORDER)
    SubscribableChannel receiveChannel();

}
