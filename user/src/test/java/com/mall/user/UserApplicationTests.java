package com.mall.user;

import com.alibaba.fastjson.JSON;
import com.mall.user.dao.entity.UserEntity;
import com.mall.user.dao.mapper.UserMapper;
import com.mall.user.util.DesSecretUtil;
import com.mall.user.util.RandomUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserApplicationTests {

    @Resource
    private UserMapper userMapper;

    @Test
    public void insert() {
        String pwd = "000000";
        String salt = RandomUtil.generateString(8);
        String encryptPwd = DesSecretUtil.encrypt(pwd, salt);

        UserEntity userEntity = new UserEntity();
        userEntity.setAccount("amos");
        userEntity.setUsername("amos.wang");
        userEntity.setSalt(salt);
        userEntity.setPassword(encryptPwd);
        userEntity.setCreateTime(LocalDateTime.now());

        UserEntity save = userMapper.save(userEntity);
        System.out.println(JSON.toJSONString(save));
    }

    @Test
    public void findById() {
        Optional<UserEntity> optional = userMapper.findById("");
        optional.ifPresent(userEntity -> System.out.println(JSON.toJSONString(userEntity)));
    }

}
