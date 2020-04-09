package com.mall.gateway.core.mq.source;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * 模块名称: mall-gateway
 * 模块描述: 自定义消息通道
 *
 * @author amos.wang
 * @date 2020/3/18 10:15
 */
public interface HelloSource {

    /**
     * 发消息的通道名称
     */
    String HELLO_CHAN = "hello_chan";

    /**
     * 发送消息通道
     *
     * @return MessageChannel
     */
    @Output(HelloSource.HELLO_CHAN)
    MessageChannel sendChannel();

}
