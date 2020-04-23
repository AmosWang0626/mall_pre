package com.mall.order.pojo.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * PROJECT: order
 * DESCRIPTION: note
 *
 * @author Daoyuan
 * @date 2018/12/7
 */
@Data
@Accessors(chain = true)
public class OrderDetailForm implements Serializable {

    private static final long serialVersionUID = 64141582908771267L;

    @ApiModelProperty(value = "商品编号", example = "PROD20200422225715507993d376bc38")
    private String productNo;

    @ApiModelProperty(value = "购买数量", example = "3")
    private Integer buyCount;

}
