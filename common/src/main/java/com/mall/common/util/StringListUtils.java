package com.mall.common.util;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * PROJECT: estate
 * DESCRIPTION: note
 *
 * @author daoyuan
 * @date 2019/8/18 11:48
 */
public class StringListUtils {

    /**
     * 数组每个 item 追加 splitKey 成 String
     */
    public static <T> String append(List<T> list, String splitKey) {
        if (list == null || list.size() == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0, len = list.size(); i < len; i++) {
            if (list.get(i) == null) {
                continue;
            }
            sb.append(list.get(i));
            if (i < len - 1) {
                if (list.get(i + 1) == null) {
                    continue;
                }
                sb.append(splitKey);
            }
        }
        return sb.toString();
    }

    /**
     * 字符串根据 splitKey 转化成 List
     * 暂仅支持转换成Long类型
     */
    public static List<Long> split(String str, String splitKey) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        List<Long> list = new ArrayList<>();
        String[] split = str.split(splitKey);
        for (String id : split) {
            if (StringUtils.isNumeric(id)) {
                list.add(Long.valueOf(id));
            }
        }
        return list;
    }

    /**
     * 字符串根据 splitKey 转化成 List, 并截取 [0, (list.size() - subEnd)]
     * 暂仅支持转换成Long类型
     */
    public static List<Long> subSplit(String str, String splitKey, int subEnd) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        List<Long> list = new ArrayList<>();
        String[] split = str.split(splitKey);
        for (int i = 0; i < split.length - subEnd; i++) {
            if (StringUtils.isNumeric(split[i])) {
                list.add(Long.valueOf(split[i]));
            }
        }
        return list;
    }

    public static void main(String[] args) {
        String string = "123,123,234";
        System.out.println("原始字符串: " + string);
        List<Long> split = split("123,123,234", ",");
        System.out.println("原始字符串按 ',' 拆分后的数组: " + split);
        assert split != null;
        string = append(split, ".");
        System.out.println("数组按 '.' 拼接: " + string);
        System.out.println("字符串按 '.' 拆分: " + split(string, "\\."));
    }

}
