package com.mall.order.core.service.impl;

import com.mall.common.base.GenericResponse;
import com.mall.order.common.ProductException;
import com.mall.order.core.service.ProductService;
import com.mall.order.dao.entity.ProductEntity;
import com.mall.order.dao.mapper.ProductMapper;
import com.mall.order.pojo.form.ProductForm;
import com.mall.order.pojo.vo.ProductVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 模块名称: mall
 * 模块描述: 商品相关
 *
 * @author amos.wang
 * @date 2020/4/22 17:36
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductMapper productMapper;


    @Override
    public GenericResponse<ProductVO> save(ProductForm form) {
        productMapper.save(new ProductEntity()
                .setName(form.getName())
                .setProductNo(form.getProductNo())
                .setUnitPrice(form.getUnitPrice())
                .setDescription(form.getDescription()));
        return GenericResponse.format(GenericResponse.SUCCESS);
    }

    @Override
    public ProductVO getByProductNo(String no) {
        return productMapper.findByProductNo(no)
                .map(entity -> new ProductVO()
                        .setName(entity.getName())
                        .setProductNo(entity.getProductNo())
                        .setUnitPrice(entity.getUnitPrice())
                        .setDescription(entity.getDescription()))
                .orElseThrow(() -> new ProductException("找不到该商品"));
    }

    @Override
    public GenericResponse<List<ProductVO>> list(ProductForm form) {
        List<ProductVO> productList =
                Optional.of(productMapper.findAll())
                        // list<entity>
                        .map(entities -> entities.stream()
                                .map(entity -> new ProductVO()
                                        .setName(entity.getName())
                                        .setProductNo(entity.getProductNo())
                                        .setUnitPrice(entity.getUnitPrice())
                                        .setDescription(entity.getDescription()))
                                // list<vo>
                                .collect(Collectors.toList()))
                        .orElse(Collections.emptyList());
        return new GenericResponse<>(productList);
    }
}
