package com.mall.user.core.mq;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;


/**
 * 模块名称: MQ生产者
 * 模块描述: 定义消息的推送管道 source
 *
 * @author amos.wang
 * @date 2020/3/17 18:09
 */
@EnableBinding(HelloSink.class)
public class MessageConsumer {

    @StreamListener(HelloSink.HELLO_CHAN)
    public void receive(Object payload) {
        System.out.println("收到消息啦: " + payload);
    }

}
