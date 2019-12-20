package com.mall.common.util;

/**
 * PROJECT: estate
 * DESCRIPTION: 脱敏工具类
 *
 * @author amos
 * @date 2019/7/14
 */
public class EncryptUtils {

    /**
     * 手机号加密
     *
     * @param phoneNo 手机号
     * @return 加密后的手机号
     */
    public static String encryptPhoneNo(String phoneNo) {
        return phoneNo.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }

}
