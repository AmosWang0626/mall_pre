package com.mall.gateway.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * PROJECT: gateway
 * DESCRIPTION: note
 *
 * @author Daoyuan
 * @date 2018/12/7
 */
@Configuration
public class BeanRegisterConfig {

    /**
     * @code @LoadBalanced 负载均衡支持
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * @code @LoadBalanced 负载均衡一霸，但是访问外网地址，会抛异常
     * eg. java.lang.IllegalStateException: No instances available for google.com
     */
    @Bean
    public RestTemplate simpleRestTemplate() {
        return new RestTemplate();
    }

}
