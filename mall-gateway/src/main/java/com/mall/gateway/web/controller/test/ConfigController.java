package com.mall.gateway.web.controller.test;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * DESCRIPTION: Nacos Config Center Controller
 *
 * @author <a href="mailto:amos.wang@xiaoi.com">amos.wang</a>
 * @date 2/17/2020
 */
@RestController
@RefreshScope
@RequestMapping("config")
@Api(tags = "Z01-Nacos配置")
public class ConfigController {

    @Value("${email:default@amos.wang}")
    private String email;

    @GetMapping("email")
    @ApiOperation(value = "获取配置的邮箱")
    public Mono<String> email() {
        return Mono.just(email);
    }

}
