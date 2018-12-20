package com.mall.user.dao.mapper;

import com.mall.user.dao.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * PROJECT: user
 * DATE: 2018/1/14
 *
 * @author DaoYuanWang
 */
@Repository
public interface UserMapper extends CrudRepository<UserEntity, Long> {

    /**
     * 根据手机号查询
     *
     * @param phone 手机号
     * @return 用户对象
     */
    UserEntity findByPhoneNo(String phone);

    /**
     * 根据邮箱查询
     *
     * @param email 邮箱
     * @return 用户对象
     */
    UserEntity findByEmail(String email);

    /**
     * 根据手机号/邮箱查询
     *
     * @param phone 手机号
     * @param email 邮箱
     * @return 用户对象
     */
    UserEntity findByPhoneNoOrEmail(String phone, String email);

}
