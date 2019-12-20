package com.mall.user.common.enums;

/**
 * PROJECT: user
 * DESCRIPTION: 积分类型
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
