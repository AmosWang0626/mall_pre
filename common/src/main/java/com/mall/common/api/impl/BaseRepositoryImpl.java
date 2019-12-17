package com.mall.common.api.impl;

import com.mall.common.api.BaseRepository;
import com.mall.common.pojo.dao.BaseEntity;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * DESCRIPTION: BaseRepositoryImpl
 *
 * @author <a href="mailto:amos.wang@xiaoi.com">amos.wang</a>
 * @date 2019/12/17
 */
@NoRepositoryBean
public class BaseRepositoryImpl<T extends BaseEntity, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {

    public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void deleteByLogic(T entity) {
        entity.setDeleteFlag(true);
        super.save(entity);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void deleteByLogic(ID id) {
        super.deleteById(id);
    }

}
