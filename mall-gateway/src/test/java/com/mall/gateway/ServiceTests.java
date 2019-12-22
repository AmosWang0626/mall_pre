package com.mall.gateway;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * DESCRIPTION: 各服务测试
 *
 * @author <a href="mailto:amos.wang@xiaoi.com">amos.wang</a>
 * @date 2019/12/22
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceTests.class);

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private LoadBalancerClient loadBalancerClient;

    /**
     * 用户系统
     */
    @GetMapping("user")
    public String user() {
        return restTemplate.getForEntity("http://mall-user/user/test/", String.class).getBody();
    }

    /**
     * 积分系统
     */
    @GetMapping("integral")
    public String integral() {
        return restTemplate.getForEntity("http://mall-user/integral/test/", String.class).getBody();
    }

    /**
     * 订单系统
     */
    @GetMapping("order")
    public String order() {
        return restTemplate.getForEntity("http://mall-order/order/test/", String.class).getBody();
    }

    /**
     * 库存系统
     */
    @GetMapping("inventory")
    public String inventory() {
        return restTemplate.getForEntity("http://mall-warehouse/inventory/test/", String.class).getBody();
    }

    /**
     * 仓储系统
     */
    @GetMapping("warehouse")
    public String warehouse() {
        return restTemplate.getForEntity("http://mall-warehouse/warehouse/test/", String.class).getBody();
    }

    @GetMapping("log-user")
    public void logUser() {
        ServiceInstance serviceInstance = loadBalancerClient.choose("mall-user");
        LOGGER.info("{}:{}:{}", serviceInstance.getServiceId(), serviceInstance.getHost(), serviceInstance.getPort());
    }

}
