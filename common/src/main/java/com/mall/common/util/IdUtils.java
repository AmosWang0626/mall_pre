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

    public static void main(String[] args) {
        System.out.println(defaultId());
    }

}
