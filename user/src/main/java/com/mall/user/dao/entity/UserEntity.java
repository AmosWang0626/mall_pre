package com.mall.user.dao.entity;

import com.mall.common.pojo.dao.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * PROJECT: user
 * DESCRIPTION: note
 *
 * @author Daoyuan
 * @date 2018/12/11
 */
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
@Entity
@Table(name = "org_user")
public class UserEntity extends BaseEntity {

    private String account;
    private String username;
    private String salt;
    private String password;
    private Integer status;

}


