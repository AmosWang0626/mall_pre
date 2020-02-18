package com.mall.common.util;

import com.mall.common.constant.MagicValueConstant;

import java.util.Random;

/**
 * @author amos.wang
 */
public final class GenerateIdUtil {

    private static Random random = new Random();

    public static String generateId(String type) {
        return DateUtils.getDateSimple() + type + System.currentTimeMillis() + random3Code();
    }

    private static String random3Code() {
        int rand = random.nextInt(1000);
        if (rand < MagicValueConstant.TEN) {
            return "00" + rand;
        } else if (rand < MagicValueConstant.HUNDRED) {
            return "0" + rand;
        }
        return rand + "";
    }

    public static void main(String[] args) {
        System.out.println(GenerateIdUtil.generateId("USER"));
    }

}
