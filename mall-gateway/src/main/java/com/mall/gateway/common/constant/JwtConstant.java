package com.mall.gateway.common.constant;

import com.mall.common.auth.JwtBaseConstant;

/**
 * DESCRIPTION: JWT Constant
 *
 * @author <a href="mailto:amos.wang@xiaoi.com">amos.wang</a>
 * @date 2019/12/21
 */
public interface JwtConstant extends JwtBaseConstant {

    /**
     * jwt 超时时间 12小时 (12 * 60 * 60 * 1000) 毫秒
     */
    long JWT_EXPIRE_TIME = 43200000;

    /**
     * token 解密信息默认保存 36小时 (12 * 60 * 60) 秒
     */
    int SECRET_EXPIRE_TIME = 129600;

    /**
     * 长时间无操作自动退出 5分钟 (5 * 60) 秒
     */
    int OPERATIONAL_EXPIRE_TIME = 300;

}
