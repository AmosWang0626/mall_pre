package com.mall.common.api;

import com.mall.common.pojo.dao.BaseEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

/**
 * DESCRIPTION: 封装 JPA 默认 CRUD
 *
 * @author <a href="mailto:amos.wang@xiaoi.com">amos.wang</a>
 * @date 2019/12/17
 */
@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity, ID> extends CrudRepository<T, ID> {

    /**
     * 根据 条件 进行逻辑删除
     *
     * @param entity condition
     */
    default void deleteLogic(T entity) {
        entity.setDeleteFlag(true);
        save(entity);
    }

    /**
     * 根据 ID 进行逻辑删除
     *
     * @param id ID
     */
    @Modifying
    @Transactional(rollbackFor = RuntimeException.class)
    @Query("update #{#entityName} e set e.deleteFlag=true where e.id=?1")
    void deleteLogic(ID id);

}
