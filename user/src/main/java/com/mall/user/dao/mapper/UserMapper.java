package com.mall.user.dao.mapper;

import com.mall.user.dao.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * PROJECT: user
 * DATE: 2018/1/14
 *
 * @author DaoYuanWang
 */
@Repository
public interface UserMapper extends CrudRepository<UserEntity, String> {

    Optional<UserEntity> findByAccount(String account);

}
