package com.mall.user.dao.entity;

import com.mall.user.enums.IntegralChangeType;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * PROJECT: user
 * DESCRIPTION: note
 *
 * @author Daoyuan
 * @date 2019/1/11
 */
@Data
@Entity(name = "integral_record")
public class IntegralRecordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderId;
    private String change;
    @Enumerated(EnumType.STRING)
    private IntegralChangeType type;
    private String reason;
    private Date createTime;
    private Date updateTime;

}
