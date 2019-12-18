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
        Optional<UserEntity> optional = userMapper.findById("402881ed6f1451c1016f1452403f0000");
        optional.ifPresent(userEntity -> System.out.println(JSON.toJSONString(userEntity)));
    }

    @Test
    public void deleteById() {
        userMapper.deleteById("402881ed6f1451c1016f1452403f0000");
    }

    @Test
    public void logicDeleteById() {
        userMapper.deleteLogic("402881ed6f190e67016f190e76400000");
    }

    @Test
    public void logicDeleteByEntity() {
        userMapper.findById("402881ed6f190e67016f190e76400000").ifPresent(userEntity -> {
            System.out.println(JSON.toJSONString(userEntity));
            userMapper.deleteLogic(userEntity);
        });
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
