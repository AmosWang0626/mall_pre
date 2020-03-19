package com.mall.user.dao.mapper;

import com.mall.common.api.BaseRepository;
import com.mall.user.dao.entity.DbLockEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * PROJECT: InnoDB Lock Test
 * DATE: 2018/1/14
 *
 * @author DaoYuanWang
 */
@Repository
public interface DbLockMapper extends BaseRepository<DbLockEntity, String> {

    /**
     * 根据条件查询
     *
     * @param account    账号
     * @param currentDay 当前日期
     * @return result
     */
    @Query("from #{#entityName} e where e.account=?1 and e.currentDay=?2")
    DbLockEntity findPrimary(Long account, String currentDay);

}
