package com.mall.gateway.feign;

import com.mall.common.base.GenericResponse;
import com.mall.common.pojo.response.AuthUserVO;
import com.mall.gateway.common.pojo.request.LoginRequest;
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
            public GenericResponse<AuthUserVO> register(LoginRequest register) {
                LOGGER.error(ERROR_LOG, Thread.currentThread().getStackTrace()[1].getMethodName(), throwable.getMessage());
                return GenericResponse.format(GenericResponse.SYSTEM_ERROR);
            }

            @Override
            public GenericResponse<AuthUserVO> login(LoginRequest login) {
                LOGGER.error(ERROR_LOG, Thread.currentThread().getStackTrace()[1].getMethodName(), throwable.getMessage());
                return GenericResponse.format(GenericResponse.SYSTEM_ERROR);
            }

            @Override
            public GenericResponse<AuthUserVO> authLoginInfo(LoginRequest login) {
                LOGGER.error(ERROR_LOG, Thread.currentThread().getStackTrace()[1].getMethodName(), throwable.getMessage());
                return GenericResponse.format(GenericResponse.SYSTEM_ERROR);
            }
        };
    }

}
