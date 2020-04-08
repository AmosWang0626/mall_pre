package com.mall.common;

import com.mall.common.pojo.response.AuthUserVO;

public class CommonTests {

    public static void main(String[] args) {
        System.out.println(new AuthUserVO().setAccount("111").setPassword("222"));
    }

}
