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
@Table(name = "po_order_detail")
public class OrderDetailEntity extends BaseEntity {

    /**
     * 订单编号
     */
    private String orderNo;
    /**
     * 商品ID
     */
    private String productNo;
    /**
     * 商品名称（冗余字段）
     */
    private String productName;
    /**
     * 商品单价（冗余字段）
     */
    private BigDecimal unitPrice;
    /**
     * 商品数量
     */
    private Integer buyCount;
    /**
     * 收货地址
     */
    private String userAddress;

}
