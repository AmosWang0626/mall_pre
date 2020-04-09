package com.mall.user.core.mq;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * 模块名称: mall-user
 * 模块描述: 自定义消息通道
 *
 * @author amos.wang
 * @date 2020/4/9 21:23
 */
public interface HelloSink {

    /**
     * 接收消息的通道名称
     */
    String HELLO_CHAN = "hello_chan";


    /**
     * 接收消息通道
     *
     * @return SubscribableChannel
     */
    @Input(HelloSink.HELLO_CHAN)
    SubscribableChannel receiveChannel();

}
