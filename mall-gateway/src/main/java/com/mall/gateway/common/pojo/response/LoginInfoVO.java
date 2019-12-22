package com.mall.gateway.common.pojo.response;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * DESCRIPTION: 登录信息
 *
 * @author <a href="mailto:amos.wang@xiaoi.com">amos.wang</a>
 * @date 2019/12/21
 */
@Data
@Accessors(chain = true)
public class LoginInfoVO {


    /**
     * 用户编号
     */
    private String userId;
    /**
     * 账号
     */
    private String account;
    /**
     * 用户名
     */
    private String username;
    /**
     * token
     */
    private String token;

}
