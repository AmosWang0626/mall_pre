package com.mall.order.core.mq.service.impl;

import com.mall.order.core.mq.service.OrderProvider;
import com.mall.order.core.mq.source.OrderSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@EnableBinding(OrderSource.class)
public class OrderProviderImpl implements OrderProvider {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Resource(name = OrderSource.ORDER)
    private MessageChannel output;

    @Override
    public boolean notice(String message) {
        LOGGER.info("MQ 发送成功: " + message);
        return output.send(MessageBuilder.withPayload(message).build());
    }

}
