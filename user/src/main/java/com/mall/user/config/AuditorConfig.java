package com.mall.user.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * DESCRIPTION: default operator user and time
 *
 * @author amos
 * @date 2019-12-17
 */
@Configuration
public class AuditorConfig implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("amos.wang");
    }

}
