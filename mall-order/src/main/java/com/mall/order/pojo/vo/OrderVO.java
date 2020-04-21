package com.mall.order.pojo.vo;

import com.mall.common.pojo.response.BaseVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 模块名称: mall
 * 模块描述: 订单信息 VO
 *
 * @author amos.wang
 * @date 2020/4/21 17:47
 */
@Getter
@Setter
@Accessors(chain = true)
public class OrderVO extends BaseVO {

    @ApiModelProperty("订单编号")
    private String orderNo;

    @ApiModelProperty("订单创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("订单详情")
    private List<OrderDetailVO> detailList;

}
