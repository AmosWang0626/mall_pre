package com.mall.common.util;

import java.util.UUID;

/**
 * DESCRIPTION: 生成 ID 工具类
 *
 * @author amos.wang
 * @date 2019/7/7
 */
public class IdUtils {

    public static String defaultId() {
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replaceAll("-", "");
        return uuid;
    }

    /**
     * 商品编号
     */
    public static String productNo() {
        return "PROD" + DateUtils.getDateTimeSimple() + defaultId().substring(18);
    }

    /**
     * 订单编号
     */
    public static String orderNo() {
        return "ODER" + DateUtils.getDateTimeSimple() + defaultId().substring(18);
    }

    public static void main(String[] args) {
        System.out.println(defaultId());
        System.out.println(productNo());
        System.out.println(orderNo());
    }

}
