package com.mall.gateway.core.mq.source;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * 模块名称: mall-gateway
 * 模块描述: HelloSource
 *
 * @author amos.wang
 * @date 2020/3/18 10:15
 */
public interface HelloSource {

    /**
     * 发消息的通道名称
     */
    String HELLO_OUTPUT = "hello-out";
    /**
     * 消息的订阅通道名称
     */
    String HELLO_INPUT = "hello-in";


    /**
     * 发送消息通道
     */
    @Output(HelloSource.HELLO_OUTPUT)
    MessageChannel sendChannel();


    /**
     * 接收消息通道
     */
    @Input(HelloSource.HELLO_INPUT)
    SubscribableChannel receiveChannel();

}
