package com.mall.gateway.config;

import com.mall.common.constant.ServiceConstant;
import com.mall.common.response.GenericResponse;
import com.mall.gateway.common.exception.GatewayExceptionEnum;
import com.mall.gateway.common.pojo.dto.AuthUserVO;
import com.mall.gateway.common.pojo.request.LoginRequest;
import com.mall.gateway.common.utils.JwtUtils;
import com.mall.gateway.feign.UserFeignClient;
import lombok.extern.java.Log;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

/**
 * DESCRIPTION: 网关过滤器
 *
 * @author <a href="mailto:amos.wang@xiaoi.com">amos.wang</a>
 * @date 2019/12/21
 */
@Log
@Component
public class JwtTokenFilter implements GlobalFilter, Ordered {

    @Resource
    private UserFeignClient userFeignClient;


    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String url = exchange.getRequest().getURI().getPath();

        Set<String> passUrls = new HashSet<>();
        passUrls.add("/user/login");
        passUrls.add("/user/register");

        if (passUrls.contains(url)) {
            return chain.filter(exchange);
        }

        String token = exchange.getRequest().getHeaders().getFirst("token");
        ServerHttpResponse response = exchange.getResponse();

        if (StringUtils.isBlank(token)) {
            return authError(response, GenericResponse.REQUEST_ILLEGAL);
        }

        GenericResponse<AuthUserVO> loginInfo = userFeignClient.authLoginInfo(new LoginRequest().setAccount(JwtUtils.getAccount(token)));
        AuthUserVO authUserVO = loginInfo.getBody();
        if (authUserVO == null) {
            return authError(response, new GenericResponse(GatewayExceptionEnum.USER_ACCOUNT_NOT_EXIST));
        }

        if (JwtUtils.isLoginExpired(token, authUserVO.getAccount(), authUserVO.getPassword())) {
            return authError(response, new GenericResponse(GatewayExceptionEnum.USER_TOKEN_EXPIRED));
        }

        if (JwtUtils.isLoginElsewhere(authUserVO.getUserId(), token)) {
            return authError(response, new GenericResponse(GatewayExceptionEnum.USER_ACCOUNT_LOGIN_ELSEWHERE));
        }

        // 在 header 中设置 user_id, 方便业务系统拿到 user_id
        ServerHttpRequest request = exchange.getRequest().mutate()
                .header(ServiceConstant.USER_ID, authUserVO.getUserId())
                .build();

        return chain.filter(exchange.mutate().request(request).build());
    }

    private Mono<Void> authError(ServerHttpResponse response, GenericResponse genericResponse) {
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        DataBuffer buffer = response.bufferFactory().wrap(genericResponse.toString().getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Flux.just(buffer));
    }

}
