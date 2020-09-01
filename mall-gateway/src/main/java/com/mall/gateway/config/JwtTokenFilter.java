package com.mall.gateway.config;

import com.alibaba.fastjson.JSON;
import com.mall.common.base.GenericResponse;
import com.mall.common.constant.ServiceConstant;
import com.mall.common.pojo.response.AuthUserVO;
import com.mall.common.util.RedisUtils;
import com.mall.gateway.common.constant.JwtConstant;
import com.mall.gateway.common.exception.GatewayExceptionEnum;
import com.mall.gateway.common.utils.JwtUtils;
import com.mall.user.api.pojo.request.LoginRequest;
import com.mall.user.api.service.IUserLoginService;
import lombok.extern.java.Log;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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

    @Reference(
            version = "1.0.0",
            interfaceClass = IUserLoginService.class,
            cluster = "failfast"
    )
    private IUserLoginService iUserLoginService;


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
        passUrls.add("/mall-user/v2/api-docs");
        passUrls.add("/mall-order/v2/api-docs");

        if (passUrls.contains(url)) {
            return chain.filter(exchange);
        }

        String token = exchange.getRequest().getHeaders().getFirst(ServiceConstant.TOKEN);
        ServerHttpResponse response = exchange.getResponse();

        if (StringUtils.isBlank(token)) {
            return authError(response, GenericResponse.REQUEST_ILLEGAL);
        }

        String account = JwtUtils.getAccount(token);
        if (StringUtils.isBlank(account)) {
            return authError(response, GenericResponse.REQUEST_ILLEGAL);
        }

        // 获取解密 token 密码
        AuthUserVO authUserVO;
        String secretInfo = RedisUtils.get(JwtConstant.REDIS_KEY_SECRET + account, 1);
        if (StringUtils.isNotBlank(secretInfo)) {
            authUserVO = JSON.parseObject(secretInfo, AuthUserVO.class);
            if (authUserVO == null) {
                return authError(response, GenericResponse.REQUEST_ILLEGAL);
            }
        } else {
            GenericResponse<AuthUserVO> loginInfo = iUserLoginService.authLoginInfo(new LoginRequest().setAccount(account));
            authUserVO = loginInfo.getBody();
            if (authUserVO == null) {
                return authError(response, GenericResponse.REQUEST_ILLEGAL);
            }
            RedisUtils.set(JwtConstant.REDIS_KEY_SECRET + account, JSON.toJSONString(authUserVO), 1, JwtConstant.SECRET_EXPIRE_TIME);
        }

        // 校验 token 是否过期
        if (JwtUtils.isLoginExpired(token, authUserVO.getAccount(), authUserVO.getPassword())) {
            return authError(response, new GenericResponse<>(GatewayExceptionEnum.USER_TOKEN_EXPIRED));
        }

        // 校验 其他设备 登录
        if (JwtUtils.isLoginElsewhere(authUserVO.getUserId(), token)) {
            return authError(response, new GenericResponse<>(GatewayExceptionEnum.USER_ACCOUNT_LOGIN_ELSEWHERE));
        }

        // 校验 长时间不操作自动退出
        if (StringUtils.isBlank(RedisUtils.get(JwtConstant.REDIS_KEY_OPERATIONAL + authUserVO.getUserId(), 1))) {
            return authError(response, new GenericResponse<>(GatewayExceptionEnum.USER_IDLE_TIME_TO_LONG));
        }
        JwtUtils.saveOperational(authUserVO.getUserId(), account);

        return chain.filter(exchange);
    }

    private Mono<Void> authError(ServerHttpResponse response, GenericResponse<String> genericResponse) {
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        DataBuffer buffer = response.bufferFactory().wrap(genericResponse.toString().getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Flux.just(buffer));
    }

}
