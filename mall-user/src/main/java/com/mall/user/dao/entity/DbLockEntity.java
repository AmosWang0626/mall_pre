package com.mall.user.dao.entity;

import com.mall.common.pojo.dao.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 模块名称: DbLockEntity
 * 模块描述: DbLockEntity
 *
 * @author amos.wang
 * @date 2020/3/18 15:50
 */
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Getter
@Setter
@Entity
@Table(name = "db_lock")
public class DbLockEntity extends BaseEntity {

    private Long account;

    private String currentDay;

    private String description;

}
