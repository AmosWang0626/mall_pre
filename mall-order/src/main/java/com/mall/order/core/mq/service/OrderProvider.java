package com.mall.order.core.mq.service;

/**
 * 模块名称: MQ 生产者
 * 模块描述: MQ 生产者
 *
 * @author amos.wang
 * @date 2020/3/17 18:09
 */
public interface OrderProvider {

    /**
     * 发送消息
     *
     * @param message 消息
     * @return status
     */
    boolean notice(String message);

}
