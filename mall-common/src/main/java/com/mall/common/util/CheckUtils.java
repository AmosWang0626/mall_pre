package com.mall.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 检查手机号和身份证号
 * PROJECT: base
 * DATE: 2017/11/30
 *
 * @author DaoYuanWang
 */
public class CheckUtils {

    private static final String ID_NO_CHECK = "^(\\d{6})(\\d{4})(\\d{2})(\\d{2})(\\d{3})([0-9]|X)$";
    private static final String PHONE_NO_CHECK = "^1([3-9])\\d{9}$";
    private static final char BANK_CARD_ID_ERROR = 'N';
    private static final int MIN_BANK_CARD_ID = 15;
    private static final int MAX_BANK_CARD_ID = 19;

    /**
     * 判断是不是手机号
     *
     * @param phoneNo 手机号
     * @return true: 不是; false: 是
     */
    public static boolean isNotPhoneNo(String phoneNo) {
        Pattern pattern = Pattern.compile(PHONE_NO_CHECK);
        Matcher matcher = pattern.matcher(phoneNo);
        return !matcher.matches();
    }

    /**
     * 判断是不是身份证号
     *
     * @param identity 身份证号
     * @return true: 不是; false: 是
     */
    public static boolean isNotIdentity(String identity) {
        Pattern pattern = Pattern.compile(ID_NO_CHECK);
        Matcher matcher = pattern.matcher(identity);
        return !matcher.matches();
    }

    /**
     * 校验银行卡卡号
     *
     * @param bankCard 银行卡号
     * @return true: 是银行卡号; false: 不是
     */
    private static boolean checkBankCard(String bankCard) {
        if (bankCard.length() < MIN_BANK_CARD_ID || bankCard.length() > MAX_BANK_CARD_ID) {
            return false;
        }
        char bit = getBankCardCheckCode(bankCard.substring(0, bankCard.length() - 1));
        return BANK_CARD_ID_ERROR != bit && bankCard.charAt(bankCard.length() - 1) == bit;
    }

    /**
     * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位
     * <p>
     * 校验过程：
     * 1、从卡号最后一位数字开始，逆向将奇数位(1、3、5等等)相加。
     * 2、从卡号最后一位数字开始，逆向将偶数位数字，先乘以2（如果乘积为两位数，将个位十位数字相加，即将其减去9），再求和。
     * 3、将奇数位总和加上偶数位总和，结果应该可以被10整除。
     */
    private static char getBankCardCheckCode(String nonCheckCodeBankCard) {
        if (nonCheckCodeBankCard == null || nonCheckCodeBankCard.trim().length() == 0
                || !nonCheckCodeBankCard.matches("\\d+")) {
            //如果传的不是数据返回N
            return 'N';
        }
        char[] chs = nonCheckCodeBankCard.trim().toCharArray();
        int luhmSum = 0;
        for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if (j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;
        }
        return (luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0');
    }

    public static void main(String[] args) {
        System.out.println(checkBankCard("6228480402637874213"));
    }
}