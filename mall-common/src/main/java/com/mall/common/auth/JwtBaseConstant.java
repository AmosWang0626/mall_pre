package com.mall.common.auth;

/**
 * DESCRIPTION: JWT Constant
 *
 * @author <a href="mailto:amos.wang@xiaoi.com">amos.wang</a>
 * @date 2019/12/21
 */
public interface JwtBaseConstant {

    String USER_ID = "userId";
    String USERNAME = "username";
    String ACCOUNT = "account";
    String LOGIN_ID = "loginId";

    /**
     * 用于 单设备登录
     */
    String REDIS_KEY_LOGIN_ID = "login:";
    /**
     * 用于 token 解密
     */
    String REDIS_KEY_SECRET = "secret:";
    /**
     * 用于 实现n分钟不操作限制登录
     */
    String REDIS_KEY_OPERATIONAL = "operational:";

}
