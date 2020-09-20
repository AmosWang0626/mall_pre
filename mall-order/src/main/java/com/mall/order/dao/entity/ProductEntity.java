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
@Table(name = "po_product")
public class ProductEntity extends BaseEntity {

    /**
     * 商品ID
     */
    private String productNo;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 商品类型
     */
    private String type;
    /**
     * 商品单价
     */
    private BigDecimal unitPrice;
    /**
     * 商品描述
     */
    private String description;

}
