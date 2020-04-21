package com.mall.order.core.service;

import com.mall.common.base.GenericResponse;
import com.mall.order.pojo.vo.OrderVO;

/**
 * 模块名称: mall
 * 模块描述: 订单业务类
 *
 * @author amos.wang
 * @date 2020/4/21 17:44
 */
public interface OrderService {

    /**
     * 根据订单号查询
     *
     * @param id 订单号
     * @return 订单信息
     */
    GenericResponse<OrderVO> findById(String id);

}
