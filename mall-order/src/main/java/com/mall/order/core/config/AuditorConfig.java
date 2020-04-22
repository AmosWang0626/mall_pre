package com.mall.order.core.config;

import com.mall.common.auth.CurrentUserContext;
import com.mall.common.pojo.response.AuthUserVO;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.lang.Nullable;

import java.util.Optional;

/**
 * DESCRIPTION: default operator user and time
 *
 * @author amos
 * @date 2019-12-17
 */
@Configuration
public class AuditorConfig implements AuditorAware<String> {

    @Nullable
    @Override
    public Optional<String> getCurrentAuditor() {
        if (CurrentUserContext.getAuthUser() == null) {
            return Optional.ofNullable(getDefaultAuthUserVO().getUserId());
        }
        return Optional.ofNullable(CurrentUserContext.getAuthUser().getUserId());
    }

    private AuthUserVO getDefaultAuthUserVO() {
        return new AuthUserVO().setUserId("anonymous").setUsername("匿名用户");
    }

}
