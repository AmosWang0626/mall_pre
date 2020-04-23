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

    @ApiModelProperty(value = "用户ID", example = "8a80cb816f2bd9f6016f2bda3ee00000")
    private String userId;

    @ApiModelProperty("购买的商品信息")
    private List<OrderDetailForm> detailList;

    @ApiModelProperty(value = "优惠券编号", example = "8a80cb816f2bd9f6016f2bda3ee00000")
    private String reduceNo;

    @ApiModelProperty(value = "备注", example = "兜风专用")
    private String description;

}
