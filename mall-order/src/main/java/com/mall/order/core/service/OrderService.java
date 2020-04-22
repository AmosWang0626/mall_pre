package com.mall.order.core.service;

import com.mall.common.base.GenericResponse;
import com.mall.order.pojo.form.OrderForm;
import com.mall.order.pojo.form.OrderSearchForm;
import com.mall.order.pojo.vo.OrderVO;

import java.util.List;

/**
 * 模块名称: mall
 * 模块描述: 订单业务类
 *
 * @author amos.wang
 * @date 2020/4/21 17:44
 */
public interface OrderService {

    /**
     * 预览订单
     *
     * @param form 订单表单
     * @return 订单信息
     */
    GenericResponse<OrderVO> preview(OrderForm form);

    /**
     * 创建订单
     *
     * @param form 订单表单
     * @return 订单信息
     */
    GenericResponse<OrderVO> create(OrderForm form);

    /**
     * 根据订单号查询
     *
     * @param no 订单号
     * @return 订单信息
     */
    GenericResponse<OrderVO> getByOrderNo(String no);

    /**
     * 查询订单
     *
     * @param form 查询表单
     * @return 订单信息
     */
    GenericResponse<List<OrderVO>> list(OrderSearchForm form);

}
