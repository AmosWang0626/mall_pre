package com.mall.user;

import com.alibaba.fastjson.JSON;
import com.mall.user.dao.entity.UserEntity;
import com.mall.user.dao.mapper.UserMapper;
import com.mall.user.util.DesSecretUtil;
import com.mall.user.util.RandomUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@EntityScan("com.mall.user.dao.entity")
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
        Optional<UserEntity> optional = userMapper.findById("4028ab7b6f140c9e016f140cc3a70000");
        optional.ifPresent(userEntity -> System.out.println(JSON.toJSONString(userEntity)));
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        System.out.println(list);
        List<Integer> tempList = new ArrayList<>();
        tempList.add(list.get(0));
        tempList.addAll(list.subList(6, list.size()));
        tempList.addAll(list.subList(1, 6));
        System.out.println(tempList);
    }

}
