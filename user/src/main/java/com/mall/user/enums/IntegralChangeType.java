package com.mall.user.enums;

/**
 * PROJECT: user
 * DESCRIPTION: note
 *
 * @author Daoyuan
 * @date 2019/1/11
 */
public enum IntegralChangeType {

    /***/
    BUY("购物"),
    ACTIVITY("活动");

    private final String desc;

    IntegralChangeType(String desc) {
        this.desc = desc;
    }

    public String getKey() {
        return this.name();
    }

    public String getDesc() {
        return desc;
    }
}
