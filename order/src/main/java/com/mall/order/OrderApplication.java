package com.mall.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 商城系统-订单系统
 *
 * @author Daoyuan
 * @version 1.0.0
 */
@SpringBootApplication
@EnableEurekaClient
@EntityScan("com.mall.order.dao.entity")
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
