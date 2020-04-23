package com.mall.order.pojo.vo;

import com.mall.common.pojo.response.BaseVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * 模块名称: mall
 * 模块描述: 订单详情 VO
 *
 * @author amos.wang
 * @date 2020/4/21 18:22
 */
@Getter
@Setter
@Accessors(chain = true)
public class OrderDetailVO extends BaseVO {

    @ApiModelProperty("商品ID")
    private String productNo;

    @ApiModelProperty("商品名称")
    private String productName;

    @ApiModelProperty("商品单价")
    private BigDecimal unitPrice;

    @ApiModelProperty("商品数量")
    private Integer buyCount;

    @ApiModelProperty("收货地址")
    private String userAddress;

}
