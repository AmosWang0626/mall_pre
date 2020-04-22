package com.mall.order.pojo.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * PROJECT: order
 * DESCRIPTION: note
 *
 * @author Daoyuan
 * @date 2018/12/7
 */
@Data
public class OrderForm implements Serializable {

    private static final long serialVersionUID = -3061187999487020977L;

    @ApiModelProperty("用户ID")
    private String userId;

    @ApiModelProperty("购买的商品信息")
    private List<OrderDetailForm> detailList;

    @ApiModelProperty("优惠券编号")
    private String reduceNo;

    @ApiModelProperty("备注")
    private String description;

}
