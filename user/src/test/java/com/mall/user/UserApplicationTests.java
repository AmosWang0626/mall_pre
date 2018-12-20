package com.mall.user;

import com.alibaba.fastjson.JSON;
import com.mall.user.dao.entity.UserEntity;
import com.mall.user.dao.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserApplicationTests {

    @Resource
    private UserMapper userMapper;

    @Test
    public void findById() {
        Optional<UserEntity> optional = userMapper.findById(10000L);
        optional.ifPresent(userEntity -> System.out.println(JSON.toJSONString(userEntity)));
    }

    @Test
    public void findByPhoneNoOrEmail() {
        UserEntity userEntity = userMapper.findByPhoneNoOrEmail("18937128861", null);
        System.out.println(JSON.toJSONString(userEntity));
    }

}
