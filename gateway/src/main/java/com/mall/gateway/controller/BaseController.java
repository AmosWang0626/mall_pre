package com.mall.gateway.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mall.gateway.exception.ExceptionEnum;
import com.mall.gateway.exception.GenericResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * PROJECT: gateway
 * DESCRIPTION: note
 *
 * @author Daoyuan
 * @date 2018/12/20
 */
public class BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    /**
     * 通用 response
     *
     * @param responseEntity ResponseEntity
     * @return GenericResponse
     */
    GenericResponse genericResponse(ResponseEntity<String> responseEntity) {
        if (responseEntity != null && responseEntity.getStatusCode().is2xxSuccessful()) {
            String responseBody = responseEntity.getBody();
            LOGGER.info("register status: {}", responseBody);

            if (StringUtils.isNotBlank(responseBody)) {
                try {
                    return new ObjectMapper().readValue(responseBody.getBytes(StandardCharsets.UTF_8), GenericResponse.class);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        LOGGER.error("服务器异常! {}", responseEntity == null ? null : responseEntity.getStatusCode());

        return new GenericResponse<>(ExceptionEnum.SERVICE_BUSY);
    }

}
