package com.mall.order.dao.mapper;

import com.mall.common.api.BaseRepository;
import com.mall.order.dao.entity.OrderEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * PROJECT: order
 * DESCRIPTION: note
 *
 * @author Daoyuan
 * @date 2018/12/26
 */
@Repository
public interface OrderMapper extends BaseRepository<OrderEntity, String> {

    Optional<OrderEntity> findByOrderNo(String orderNo);

    void deleteByOrderNo(String orderNo);

}
