package com.mall.gateway.common;

import com.mall.common.base.GenericResponse;
import com.mall.gateway.common.exception.GatewayExceptionEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.function.Function;

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


    public <T> GenericResponse<T> format(ResponseEntity<GenericResponse<T>> responseEntity) {
        if (responseEntity != null && responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity.getBody();
        }

        LOGGER.error("服务器异常! ResponseEntity[{}]", responseEntity == null ? null : responseEntity.getStatusCode());

        return GenericResponse.format(GenericResponse.SYSTEM_ERROR);
    }

    public <T> Function<Throwable, Mono<? extends GenericResponse<T>>> fallback() {
        return throwable -> {
            if (throwable instanceof WebExchangeBindException) {
                return Mono.just(new GenericResponse<T>(GatewayExceptionEnum.ERROR_PARAM_FORMAT,
                        ((WebExchangeBindException) throwable).getAllErrors()));
            } else {
                return Mono.just(GenericResponse.format(GenericResponse.FAIL));
            }
        };
    }

}
