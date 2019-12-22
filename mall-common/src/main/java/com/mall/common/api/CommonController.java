package com.mall.common.api;

import com.mall.common.constant.ServiceConstant;

import javax.servlet.http.HttpServletRequest;

/**
 * DESCRIPTION: common controller
 *
 * @author <a href="mailto:amos.wang@xiaoi.com">amos.wang</a>
 * @date 2019/12/22
 */
public interface CommonController {

    /**
     * get user id
     *
     * @param request HttpServletRequest
     * @return user id
     */
    default String getUserId(HttpServletRequest request) {
        return request.getHeader(ServiceConstant.USER_ID);
    }

}
