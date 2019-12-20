package com.mall.gateway.common;

import com.mall.common.response.GenericResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * PROJECT: gateway
 * DESCRIPTION: note
 *
 * @author Daoyuan
 * @date 2018/12/20
 */
public abstract class BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    @Resource
    protected RestTemplate restTemplate;


    public GenericResponse generateResponse(ResponseEntity<GenericResponse> responseEntity) {
        if (responseEntity != null && responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity.getBody();
        }

        LOGGER.error("服务器异常! {}", responseEntity == null ? null : responseEntity.getStatusCode());

        return GenericResponse.SYSTEM_ERROR;
    }

}
