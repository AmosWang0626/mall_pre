package com.mall.gateway.core.mq.service.impl;

import com.mall.gateway.core.mq.service.MessageProvider;
import com.mall.gateway.core.mq.source.HelloSource;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;


/**
 * 模块名称: MQ生产者
 * 模块描述: 定义消息的推送管道 source
 *
 * @author amos.wang
 * @date 2020/3/17 18:09
 */
@EnableBinding(HelloSource.class)
public class MessageProviderImpl implements MessageProvider {

    @Resource(name = HelloSource.HELLO_CHAN)
    private MessageChannel output;

    @Override
    public String send(String message) {
        boolean send = output.send(MessageBuilder.withPayload(message).build());
        String status = send ? "成功" : "失败";
        System.out.printf("发送消息 [%s] 状态: %s\n", message, status);

        return status;
    }

}
