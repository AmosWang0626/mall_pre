package com.mall.gateway.common.constant;

/**
 * DESCRIPTION: JWT Constant
 *
 * @author <a href="mailto:amos.wang@xiaoi.com">amos.wang</a>
 * @date 2019/12/21
 */
public interface JwtConstant {

    /**
     * token 超时时间 (2小时 2 * 60 * 60 * 1000)
     */
    long EXPIRE_TIME = 7200000;

    String USER_ID = "userId";
    String USERNAME = "username";
    String ACCOUNT = "account";
    String LOGIN_ID = "loginId";

}
