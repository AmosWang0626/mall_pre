package com.mall.order.dao.entity;

import com.mall.common.pojo.dao.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * 模块名称: mall
 * 模块描述: 商品表
 *
 * @author amos.wang
 * @date 2020/4/21 17:58
 */
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
@Entity
@Table(name = "product")
public class ProductEntity extends BaseEntity {

    private String productNo;

    private String name;

    private BigDecimal price;

    private String description;

}
