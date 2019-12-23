package com.mall.user.config;

/**
 * DESCRIPTION: thread local save local user
 *
 * @author <a href="mailto:amos.wang@xiaoi.com">amos.wang</a>
 * @date 2019/12/23
 */
public class CurrentUserContext {

    private static ThreadLocal<String> USER_ID = new ThreadLocal<>();

    public CurrentUserContext() {
    }

    public static String getUserId() {
        return USER_ID.get();
    }

    public static void setUserId(String userId) {
        USER_ID.set(userId);
    }

    public static void removeUserId() {
        USER_ID.remove();
    }

}
