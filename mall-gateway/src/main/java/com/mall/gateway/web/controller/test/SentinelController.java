package com.mall.gateway.web.controller.test;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.mall.common.api.Token;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * DESCRIPTION: Sentinel哨兵
 *
 * @author <a href="mailto:amos.wang@xiaoi.com">amos.wang</a>
 * @date 2/19/2020
 */
@RestController
@RequestMapping("sentinel")
@Api(tags = "Z03-Sentinel测试")
public class SentinelController {

    @Token(check = false)
    @GetMapping("hello")
    @SentinelResource("/sentinel/hello")
    @ApiOperation(value = "QPS Test")
    public String hello() {
        return "Hello World!";
    }

}
