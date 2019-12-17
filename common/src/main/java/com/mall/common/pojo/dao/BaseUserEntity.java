package com.mall.common.pojo.dao;

import lombok.Getter;
import lombok.Setter;

/**
 * DESCRIPTION: Base User Entity
 *
 * @author <a href="mailto:amos.wang@xiaoi.com">amos.wang</a>
 * @date 2019/12/17
 */
@Getter
@Setter
public class BaseUserEntity extends BaseEntity {

    private String account;

    private String username;

}
