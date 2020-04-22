package com.mall.order.common;

/**
 * 模块名称: mall
 * 模块描述: ProductException
 *
 * @author amos.wang
 * @date 2020/4/22 17:55
 */
public class ProductException extends RuntimeException {

    private static final long serialVersionUID = 4869989401756038214L;

    public ProductException(String message) {
        super(message);
    }
}
