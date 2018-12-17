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
    public void contextLoads() {
        Optional<UserEntity> optional = userMapper.findById(10000L);
        optional.ifPresent(userEntity -> System.out.println(JSON.toJSONString(userEntity)));
    }

//    public static void main(String[] args) throws Exception {
//        String personInfo = "{\"age\":22,\"carrier\":\"中国电信\",\"email\":\"\",\"gender\":\"男\",\"idcard\":\"41138119960511****\",\"idcard_location\":\"河南省/南阳市/邓州市\",\"mobile\":\"1893712****\",\"mobile_location\":\"河南/郑州\",\"name\":\"王**\"}";
//
//        PersonInfoEntity personInfoEntity;
//
//        personInfoEntity = JSON.parseObject(personInfo, PersonInfoEntity.class);
//        System.out.println(JSON.toJSONString(personInfoEntity));
//
//        // 用户基本信息
//        personInfoEntity = new ObjectMapper().readValue(personInfo.getBytes(StandardCharsets.UTF_8), PersonInfoEntity.class);
//        System.out.println(JSON.toJSONString(personInfoEntity));
//    }
//
//    @Data
//    @JsonIgnoreProperties(ignoreUnknown = true)
//    private static class PersonInfoEntity {
//
//        /**
//         * 用户身份证号
//         */
//        private String idcard;
//        /**
//         * 身份证归属地. 格式为省/市/区(县)
//         */
//        @JsonProperty("idcard_location")
//        private String idcardLocation;
//        /**
//         * 用户手机号
//         */
//        private String mobile;
//        /**
//         * 手机号运营商类型. 可取值为中国移动,中国联通或中国电信
//         */
//        private String carrier;
//        /**
//         * 手机号地址
//         */
//        @JsonProperty("mobile_location")
//        private String mobileLocation;
//        /**
//         * 用户姓名
//         */
//        private String name;
//        /**
//         * 用户年龄
//         */
//        private Integer age;
//        /**
//         * 用户性别
//         */
//        private String gender;
//        /**
//         * 邮箱地址
//         */
//        private String email;
//
//    }

}
