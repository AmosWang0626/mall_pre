package com.mall.order.dao.mapper;

import com.mall.order.dao.entity.OrderDetailEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * PROJECT: order
 * DESCRIPTION: note
 *
 * @author Daoyuan
 * @date 2018/12/26
 */
@Repository
public interface OrderDetailMapper extends CrudRepository<OrderDetailEntity, String> {

    Optional<List<OrderDetailEntity>> findByOrderNo(String orderNo);

    void deleteByOrderNo(String orderNo);

}
