package com.mall.user.dao.entity;

import com.mall.common.pojo.dao.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
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
@Table(name = "integral")
public class IntegralEntity extends BaseEntity {

    /**
     * 用户编号
     */
    private String userId;
    /**
     * 积分
     */
    private Long total;
    /**
     * 备注
     */
    private String reason;

}
