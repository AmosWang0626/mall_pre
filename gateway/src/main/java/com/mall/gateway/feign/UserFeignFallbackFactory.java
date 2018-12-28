package com.mall.gateway.feign;

import com.mall.gateway.exception.ExceptionEnum;
import com.mall.gateway.exception.GenericResponse;
import com.mall.gateway.request.LoginRequest;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * PROJECT: gateway
 * DESCRIPTION: 容错配置，如果对应服务器挂掉，则进入本方法
 *
 * @author Daoyuan
 * @date 2018/12/28
 */
@Component
public class UserFeignFallbackFactory implements FallbackFactory<UserFeignClient> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserFeignFallbackFactory.class);

    private static final String ERROR_LOG = "{} Fallback capture exception is >>> {}";

    @Override
    public UserFeignClient create(Throwable throwable) {
        return new UserFeignClient() {
            @Override
            public GenericResponse register(LoginRequest register) {
                LOGGER.error(ERROR_LOG, Thread.currentThread().getStackTrace()[1].getMethodName(), throwable.getMessage());
                return new GenericResponse(ExceptionEnum.SERVICE_BUSY);
            }

            @Override
            public GenericResponse login(LoginRequest login) {
                LOGGER.error(ERROR_LOG, Thread.currentThread().getStackTrace()[1].getMethodName(), throwable.getMessage());
                return new GenericResponse(ExceptionEnum.SERVICE_BUSY);
            }
        };
    }

}
