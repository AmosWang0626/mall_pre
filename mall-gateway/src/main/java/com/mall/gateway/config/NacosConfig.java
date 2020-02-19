package com.mall.gateway.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * DESCRIPTION: NacosConfig
 *
 * @author <a href="mailto:amos.wang@xiaoi.com">amos.wang</a>
 * @date 2/19/2020
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "spring.cloud.sentinel.datasource.ds.nacos")
public class NacosConfig {

    private String serverAddr;
    private String dataId;
    private String groupId;

}
