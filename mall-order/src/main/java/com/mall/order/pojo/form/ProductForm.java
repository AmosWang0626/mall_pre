package com.mall.order.pojo.form;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 模块名称: mall
 * 模块描述: 商品表单
 *
 * @author amos.wang
 * @date 2020/4/21 18:09
 */
@Accessors(chain = true)
@Data
@ApiOperation("商品信息")
public class ProductForm implements Serializable {

    private static final long serialVersionUID = 5159898762849415647L;

    @ApiModelProperty("ID（新增不传，修改必传）")
    private String id;

    @ApiModelProperty(value = "商品名称", required = true)
    private String name;

    @ApiModelProperty(value = "商品单价", required = true)
    private BigDecimal price;

    @ApiModelProperty(value = "商品描述", required = true)
    private String description;

}
