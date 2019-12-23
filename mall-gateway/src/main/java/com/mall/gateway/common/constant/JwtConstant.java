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
     * token 超时时间 (2小时 2 * 60 * 60 * 1000)
     */
    long EXPIRE_TIME = 7200000;

}
