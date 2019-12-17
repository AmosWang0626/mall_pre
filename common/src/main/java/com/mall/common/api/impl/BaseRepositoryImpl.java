package com.mall.common.api.impl;

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
public class BaseRepositoryImpl {

//    @NoRepositoryBean
//    public class BaseRepositoryImpl<T extends BaseEntity, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {
//
//        private EntityManager em;
//    private JpaEntityInformation<T, ID> entityInformation;
//
//    public BaseRepositoryImpl(JpaEntityInformation<T, ID> entityInformation, EntityManager em) {
//        super(entityInformation, em);
//        this.entityInformation = entityInformation;
//        this.em = em;
//    }
//
//    @Override
//    @Transactional(rollbackFor = RuntimeException.class)
//    public void delete(T entity, boolean isLogicDelete) {
//        if (!isLogicDelete) {
//            super.delete(entity);
//        } else {
//            entity.setDelFlag(1);
//            super.save(entity);
//        }
//    }
//
//    @Override
//    @Transactional(rollbackFor = RuntimeException.class)
//    public void deleteById(ID id, boolean isLogicDelete) {
//        if (!isLogicDelete) {
//            super.deleteById(id);
//        } else {
//            super.findById(id).ifPresent(v -> {
//                v.setDelFlag(1);
//                super.save(v);
//            });
//        }
//    }
    
}
