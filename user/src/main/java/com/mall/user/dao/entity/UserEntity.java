package com.mall.user.dao.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * PROJECT: user
 * DESCRIPTION: note
 *
 * @author Daoyuan
 * @date 2018/12/11
 */
@Data
@Entity(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nickName;
    private String phoneNo;
    private String email;
    /**
     * 密码 / 密码盐
     */
    private String salt;
    private String password;
    private String name;
    private Boolean gender;
    private String identityNo;
    private Date createTime;
    private Date updateTime;

}
