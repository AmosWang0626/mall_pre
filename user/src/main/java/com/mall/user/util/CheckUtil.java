package com.mall.user.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * PROJECT: user
 * DESCRIPTION: note
 *
 * @author Daoyuan
 * @date 2018/12/20
 */
public class CheckUtil {

    private static final String REG_PHONE_NO = "^1[3-9]\\d{9}$";

    public static boolean isPhoneNo(String phoneNo) {
        if (StringUtils.isBlank(phoneNo)) {
            return false;
        }

        return Pattern.compile(REG_PHONE_NO).matcher(phoneNo).matches();
    }

}
