package com.mall.gateway.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * DESCRIPTION: SwaggerResourceHandler
 *
 * @author <a href="mailto:amos.wang@xiaoi.com">amos.wang</a>
 * @date 2/25/2020
 */
@Component
public class SwaggerResourceHandler implements HandlerFunction<ServerResponse> {

    @Resource
    private SwaggerProvider swaggerProvider;

    /**
     * Handle the given request.
     *
     * @param request the request to handler
     * @return the response
     */
    @Override
    public Mono<ServerResponse> handle(ServerRequest request) {
        return ServerResponse.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(swaggerProvider.get()));
    }

}
