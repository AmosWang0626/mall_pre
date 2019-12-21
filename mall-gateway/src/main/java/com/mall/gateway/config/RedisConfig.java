package com.mall.gateway.config;

import com.mall.common.util.RedisUtils;
import lombok.Data;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;

/**
 * PROJECT: base
 * DESCRIPTION: IM 配置
 *
 * @author amos
 * @date 2019/6/2
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "spring.redis")
public class RedisConfig {

    private String host;
    private Integer port;
    private String password;
    private Integer database;

    @Bean("redisPool")
    public JedisPool jedisPool() {
        JedisPool jedisPool = new JedisPool(
                new GenericObjectPoolConfig(),
                host, port, 2000, password, database, false);

        // RedisUtils 是由静态方法构成的工具类
        RedisUtils.setPool(jedisPool);

        return jedisPool;
    }

}
