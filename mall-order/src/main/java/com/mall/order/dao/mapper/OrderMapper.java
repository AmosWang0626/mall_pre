package com.mall.order.dao.mapper;

import com.mall.order.dao.entity.OrderEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * PROJECT: order
 * DESCRIPTION: note
 *
 * @author Daoyuan
 * @date 2018/12/26
 */
public interface OrderMapper extends CrudRepository<OrderEntity, String> {
}
