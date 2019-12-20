package com.mall.order.request;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * PROJECT: order
 * DESCRIPTION: note
 *
 * @author Daoyuan
 * @date 2018/12/7
 */
@Data
public class OrderRequest implements Serializable {

    private static final long serialVersionUID = -3061187999487020977L;

    /**
     * 订单编号
     */
    private String orderId;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 单价
     */
    private BigDecimal price;
    /**
     * 数量
     */
    private Integer count;
}
