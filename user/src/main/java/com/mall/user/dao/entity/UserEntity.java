package com.mall.user.dao.entity;

import com.mall.common.pojo.dao.BaseUserEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Table;

/**
 * PROJECT: user
 * DESCRIPTION: note
 *
 * @author Daoyuan
 * @date 2018/12/11
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Table(name = "org_user")
public class UserEntity extends BaseUserEntity {

    private String salt;
    private String password;
    private Integer status;

}
