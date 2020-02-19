package com.mall.gateway;

import com.mall.gateway.config.NacosConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GatewayApplicationTests {

    @Resource
    private RestTemplate restTemplate;

    @Test
    public void contextLoads() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://mall-user/user/test", String.class);
        System.out.println(responseEntity.getStatusCode());
        System.out.println(responseEntity.getBody());
    }

    @Resource
    private NacosConfig nacosConfig;

    @Test
    public void loadNacosConfig() {
        System.out.println(nacosConfig.getDataId());
        System.out.println(nacosConfig.getGroupId());
        System.out.println(nacosConfig.getServerAddr());
    }

}
