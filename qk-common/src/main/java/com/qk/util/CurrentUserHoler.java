package com.qk.util;

/**
 * 操作当前登录用户信息
 */
public class CurrentUserHoler {
    private static final ThreadLocal<Integer> CURRENT_USER = new ThreadLocal<>();

    public static void setCurrentUser(Integer userId) {
        CURRENT_USER.set(userId);
    }

    public static Integer getCurrentUser() {
        return CURRENT_USER.get();
    }

    public static void removeCurrentUser() {
        CURRENT_USER.remove();
    }
}