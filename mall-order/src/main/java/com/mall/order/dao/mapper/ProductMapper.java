package com.mall.order.dao.mapper;

import com.mall.common.api.BaseRepository;
import com.mall.order.dao.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * PROJECT: order
 * DESCRIPTION: note
 *
 * @author Daoyuan
 * @date 2020/4/21
 */
@Repository
public interface ProductMapper extends BaseRepository<ProductEntity, String>, JpaRepository<ProductEntity, String> {

    /**
     * 根据商品号查询
     *
     * @param no 商品号
     * @return 商品
     */
    Optional<ProductEntity> findByProductNo(String no);

}
