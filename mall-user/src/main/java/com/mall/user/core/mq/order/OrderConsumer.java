package com.mall.user.core.mq.order;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;


/**
 * 模块名称: MQ生产者
 * 模块描述: 定义消息的推送管道 source
 *
 * @author amos.wang
 * @date 2020/3/17 18:09
 */
@EnableBinding(OrderSink.class)
public class OrderConsumer {

    @StreamListener(OrderSink.ORDER)
    public void receive(Object payload) {
        System.out.println("mall-order: " + payload);
    }

}
