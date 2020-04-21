package com.mall.order.pojo.vo;

import com.mall.common.pojo.response.BaseVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * 模块名称: mall
 * 模块描述: 商品 VO
 *
 * @author amos.wang
 * @date 2020/4/21 18:20
 */
@Getter
@Setter
@Accessors(chain = true)
public class ProductVO extends BaseVO {

    @ApiModelProperty("商品编号")
    private String productNo;

    @ApiModelProperty("商品名称")
    private String name;

    @ApiModelProperty("商品单价")
    private BigDecimal price;

    @ApiModelProperty("商品描述")
    private String description;

}
