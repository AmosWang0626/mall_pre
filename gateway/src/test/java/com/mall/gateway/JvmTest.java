package com.mall.gateway;

import java.util.HashSet;
import java.util.Set;

/**
 * PROJECT: gateway
 * DESCRIPTION: note
 *
 * @author Daoyuan
 * @date 2018/12/21
 */
public class JvmTest {

    /**
     * 打印GC日志: -XX:+PrintGCDetails
     */
    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < 10000000; i++) {
            set.add(i);
        }
        System.out.println(set.contains(600));
    }

}
