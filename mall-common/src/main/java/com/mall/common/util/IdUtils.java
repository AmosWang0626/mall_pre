package com.mall.common.util;

import java.util.UUID;

/**
 * DESCRIPTION: 生成 ID 工具类
 *
 * @author amos.wang
 * @date 2019/7/7
 */
public class IdUtils {

    private static final Object ORDER_NO_LOCK = new Object();


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
        synchronized (ORDER_NO_LOCK) {
            return "ORDER" + DateUtils.getDateTimeSimple() + defaultId().substring(17);
        }
    }

}
