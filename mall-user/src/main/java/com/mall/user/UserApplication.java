package com.mall.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * 商城系统-用户系统
 * {@code @EnableEurekaClient} 可省略
 *
 * @author Daoyuan
 * @version 1.0.0
 */
@EnableJpaAuditing
@EnableDiscoveryClient
@SpringBootApplication
@EnableBinding(Sink.class)
@EntityScan("com.mall.user.dao.entity")
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
