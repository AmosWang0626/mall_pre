package com.mall.order.pojo.vo;

import com.mall.common.pojo.response.BaseVO;
import com.mall.order.common.OrderStatusEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;
import java.time.LocalDate;
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

    @ApiModelProperty("用户ID")
    private String userId;

    @ApiModelProperty("订单编号")
    private String orderNo;

    @ApiModelProperty("交易流水号")
    private String serialNo;

    @ApiModelProperty("消费金额")
    private BigDecimal consumeAmount;

    @Enumerated(EnumType.STRING)
    @ApiModelProperty("订单状态")
    private OrderStatusEnum orderStatus;

    @ApiModelProperty("消费日期")
    private LocalDate consumeDate;

    @ApiModelProperty("消费时间")
    private LocalDateTime consumeTime;

    @ApiModelProperty("订单详情")
    private List<OrderDetailVO> detailList;

    /**
     * 优惠
     */
    @ApiModelProperty("优惠金额")
    private BigDecimal reduceAmount;

    @ApiModelProperty("优惠原因")
    private String reduceReason;

    @ApiModelProperty("备注")
    private String description;

}
