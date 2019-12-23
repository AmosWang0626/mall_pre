package com.mall.common.auth;

import com.mall.common.pojo.response.AuthUserVO;

/**
 * DESCRIPTION: thread local save local user
 *
 * @author <a href="mailto:amos.wang@xiaoi.com">amos.wang</a>
 * @date 2019/12/23
 */
public class CurrentUserContext {

    private static ThreadLocal<AuthUserVO> USER_ID = new ThreadLocal<>();

    public CurrentUserContext() {
    }

    public static AuthUserVO getAuthUser() {
        return USER_ID.get();
    }

    public static void setAuthUser(AuthUserVO userId) {
        USER_ID.set(userId);
    }

    public static void removeAuthUser() {
        USER_ID.remove();
    }

}
