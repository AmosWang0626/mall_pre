package com.mall.common.pojo.response;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 模块名称: mall
 * 模块描述: BaseVO
 * 1. 继承 BaseVO 的好处，toString() 默认返回 json_format(this)
 *
 * @author amos.wang
 * @date 2020/4/8 16:00
 */
public abstract class BaseVO {

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

}
