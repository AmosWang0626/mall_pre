package com.mall.order.dao.entity;

import com.mall.common.pojo.dao.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * PROJECT: order
 * DESCRIPTION: 订单详情表
 *
 * @author Daoyuan
 * @date 2018/12/26
 */
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
@Entity
@Table(name = "order_detail")
public class OrderDetailEntity extends BaseEntity {

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
     * 地址
     */
    private String address;

}
