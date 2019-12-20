package com.mall.user.dao.entity;

import com.mall.common.pojo.dao.BaseEntity;
import com.mall.user.common.enums.IntegralChangeType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

/**
 * PROJECT: user
 * DESCRIPTION: 积分变动记录
 *
 * @author Daoyuan
 * @date 2019/1/11
 */
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
@Entity
@Table(name = "integral_record")
public class IntegralRecordEntity extends BaseEntity {

    /**
     * 用户积分 主ID
     */
    private String integralId;
    /**
     * 订单编号
     */
    private String orderId;
    /**
     * 积分变动(可正负)
     */
    private Long modify;
    /**
     * 积分类型
     */
    @Enumerated(EnumType.STRING)
    private IntegralChangeType type;
    /**
     * 备注
     */
    private String reason;

}
