package com.mall.warehouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 商城系统-仓储系统
 *
 * @author Daoyuan
 * @version 1.0.0
 */
@SpringBootApplication
@EnableEurekaClient
public class WarehouseApplication {

    public static void main(String[] args) {
        SpringApplication.run(WarehouseApplication.class, args);
    }
}
