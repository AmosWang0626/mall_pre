package com.mall.order.core.service.impl;

import com.mall.common.base.GenericResponse;
import com.mall.common.util.IdUtils;
import com.mall.order.common.ProductException;
import com.mall.order.core.service.ProductService;
import com.mall.order.dao.entity.ProductEntity;
import com.mall.order.dao.mapper.ProductMapper;
import com.mall.order.pojo.OrderConverter;
import com.mall.order.pojo.form.ProductForm;
import com.mall.order.pojo.vo.ProductVO;
import org.apache.commons.lang3.StringUtils;
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
    @Resource
    private OrderConverter orderConverter;


    @Override
    public GenericResponse<ProductVO> save(ProductForm form) {
        ProductEntity entity = orderConverter.convert(form);
        if (StringUtils.isNotBlank(entity.getProductNo())) {
            productMapper.findByProductNo(entity.getProductNo())
                    .ifPresent(productEntity -> entity.setId(productEntity.getId()));
        }
        if (entity.getId() == null && entity.getProductNo() == null) {
            entity.setProductNo(IdUtils.productNo());
        }

        productMapper.save(entity);

        return new GenericResponse<>(orderConverter.reduction(entity));
    }

    @Override
    public ProductVO getByProductNo(String no) {
        return productMapper.findByProductNo(no)
                .map(entity -> orderConverter.reduction(entity))
                .orElseThrow(() -> new ProductException("找不到该商品"));
    }

    @Override
    public List<ProductVO> list(ProductForm form) {
        return Optional.of(productMapper.findAll())
                // list<entity>
                .map(entities -> entities.stream()
                        .map(entity -> orderConverter.reduction(entity))
                        // list<vo>
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

    @Override
    public GenericResponse<String> delete(String no) {
        productMapper.findByProductNo(no)
                .ifPresent(productEntity -> productMapper.deleteLogic(productEntity));

        return GenericResponse.SUCCESS;
    }

}
