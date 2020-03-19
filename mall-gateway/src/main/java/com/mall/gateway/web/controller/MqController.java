package com.mall.gateway.web.controller;

import com.mall.common.base.GenericResponse;
import com.mall.gateway.core.mq.service.MessageProvider;
import com.mall.gateway.core.mq.source.HelloSource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * PROJECT: gateway
 * DESCRIPTION: note
 *
 * @author Daoyuan
 * @date 2018/12/11
 */
@RestController
@RequestMapping("mq")
@Api(tags = "C01-MQ测试")
public class MqController {

    @Resource
    private MessageProvider messageProvider;


    /**
     * 通过 MQ 发送消息
     *
     * @param message 表单
     * @return Mono
     */
    @PostMapping(value = "send/{message}")
    @ApiOperation(value = "登录接口")
    public Mono<GenericResponse<String>> send(@PathVariable("message") String message) {
        return Mono.just(new GenericResponse<>(messageProvider.send(message)));
    }

    /**
     * 接收 MQ 消息
     *
     * @param message MQ 消息
     */
    @StreamListener(HelloSource.HELLO_INPUT)
    public void receive(Message<String> message) {
        System.out.printf("接收 MQ 消息: [%s]\n", message.getPayload());
    }

}
