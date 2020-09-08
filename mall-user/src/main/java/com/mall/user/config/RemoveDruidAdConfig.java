package com.mall.user.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.alibaba.druid.spring.boot.autoconfigure.properties.DruidStatProperties;
import com.alibaba.druid.util.Utils;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * 模块名称: mall
 * 模块描述: 去除 Druid 广告配置
 *
 * @author amos.wang
 * @date 2020/9/8 16:36
 */
@Configuration
@ConditionalOnWebApplication
@AutoConfigureAfter(DruidDataSourceAutoConfigure.class)
@ConditionalOnProperty(name = "spring.datasource.druid.stat-view-servlet.enabled", havingValue = "true", matchIfMissing = true)
public class RemoveDruidAdConfig {

    /**
     * 除去页面底部的广告
     *
     * @param properties DruidStatProperties
     * @return FilterRegistrationBean
     */
    @Bean
    public FilterRegistrationBean<Filter> removeDruidAdFilterRegistrationBean(DruidStatProperties properties) {

        // 获取 web 监控页面的参数
        DruidStatProperties.StatViewServlet config = properties.getStatViewServlet();

        // 提取 common.js 的配置路径
        String pattern = config.getUrlPattern() == null ? "/druid/*" : config.getUrlPattern();
        String commonJsPattern = pattern.replaceAll("\\*", "js/common.js");

        final String filePath = "support/http/resources/js/common.js";

        // 创建 filter 进行过滤
        Filter filter = (request, response, chain) -> {
            chain.doFilter(request, response);

            // 重置缓冲区，响应头不会被重置
            response.resetBuffer();

            // 获取 common.js
            String text = Utils.readFromResource(filePath);

            // 正则替换 banner，除去底部的广告信息
            text = text.replaceAll("<a.*?banner\"></a><br/>", "");
            text = text.replaceAll("powered.*?shrek.wang</a>", "");
            response.getWriter().write(text);
        };

        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(filter);
        registrationBean.addUrlPatterns(commonJsPattern);

        return registrationBean;
    }

}
