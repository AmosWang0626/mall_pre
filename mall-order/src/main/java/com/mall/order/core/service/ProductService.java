package com.mall.order.core.service;

import com.mall.common.base.GenericResponse;
import com.mall.order.pojo.form.ProductForm;
import com.mall.order.pojo.vo.ProductVO;

import java.util.List;

/**
 * 模块名称: mall
 * 模块描述: 订单业务类
 *
 * @author amos.wang
 * @date 2020/4/21 17:44
 */
public interface ProductService {

    /**
     * 保存商品信息
     *
     * @param form 商品表单
     * @return 订单信息
     */
    GenericResponse<ProductVO> save(ProductForm form);

    /**
     * 根据商品号查询
     *
     * @param no 商品号
     * @return 订单信息
     */
    ProductVO getByProductNo(String no);

    /**
     * 查询商品
     *
     * @param form 查询表单
     * @return 订单信息
     */
    List<ProductVO> list(ProductForm form);


    /**
     * 根据商品号删除
     *
     * @param no 商品号
     * @return res
     */
    GenericResponse<String> delete(String no);

}
