package com.mall.order.dao.entity;

import com.mall.order.common.OrderStatusEnum;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * PROJECT: order
 * DESCRIPTION: 订单表
 *
 * @author Daoyuan
 * @date 2018/12/26
 */
@Data
@Entity(name = "apply_order")
public class ApplyOrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 订单编号
     */
    private String orderNo;
    /**
     * 消费日期
     */
    private Date consumeDate;
    /**
     * 消费时间
     */
    private Date consumeTime;
    /**
     * 消费金额
     */
    private BigDecimal consumeAmount;
    /**
     * 优惠金额
     */
    private BigDecimal reduceAmount;
    /**
     * 优惠原因
     */
    private String reduceReason;
    /**
     * 交易流水号
     */
    private String serialNo;
    /**
     * 订单状态
     */
    @Enumerated(EnumType.STRING)
    private OrderStatusEnum orderStatus;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;

}
