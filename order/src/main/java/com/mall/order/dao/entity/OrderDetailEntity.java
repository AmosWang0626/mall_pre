package com.mall.order.dao.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * PROJECT: order
 * DESCRIPTION: 订单详情表
 *
 * @author Daoyuan
 * @date 2018/12/26
 */
@Data
@Entity(name = "order_detail")
public class OrderDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 订单编号
     */
    private String orderNo;
    /**
     * 消费商品名称
     */
    private String productName;
    /**
     * 消费商品单价
     */
    private BigDecimal productUnitPrice;
    /**
     * 消费商品数量
     */
    private Integer productCount;
    /**
     * 消费时间
     */
    private Date consumeTime;
    /**
     * 地址
     */
    private String address;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;

}
