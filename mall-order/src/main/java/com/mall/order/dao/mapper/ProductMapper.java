package com.mall.order.dao.mapper;

import com.mall.order.dao.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * PROJECT: order
 * DESCRIPTION: note
 *
 * @author Daoyuan
 * @date 2020/4/21
 */
public interface ProductMapper extends CrudRepository<ProductEntity, String> {
}
