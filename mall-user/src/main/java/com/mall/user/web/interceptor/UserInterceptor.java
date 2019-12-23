package com.mall.user.web.interceptor;

import com.mall.common.api.Token;
import com.mall.common.auth.CurrentUserContext;
import com.mall.common.auth.JwtBaseUtils;
import com.mall.common.constant.ServiceConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author daoyuan
 */
@Component
public class UserInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader(ServiceConstant.TOKEN);
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 如果不需要校验直接 return true
        if (!needTokenCheck(handlerMethod)) {
            return true;
        }
        if (StringUtils.isBlank(token)) {
            return false;
        }

        CurrentUserContext.setAuthUser(JwtBaseUtils.getAuthUser(token));

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    }

    /**
     * 是否要检查 TOKEN
     *
     * @param handlerMethod handlerMethod
     * @return 是否需要检查 MEMBER_ID
     */
    private boolean needTokenCheck(HandlerMethod handlerMethod) {
        Method method = handlerMethod.getMethod();
        Token annotation = method.getAnnotation(Token.class);
        return (null == annotation || annotation.check());
    }
}
