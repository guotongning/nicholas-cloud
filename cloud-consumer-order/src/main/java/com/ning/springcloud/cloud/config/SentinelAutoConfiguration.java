package com.ning.springcloud.cloud.config;

import com.alibaba.cloud.sentinel.SentinelProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Configuration
@EnableConfigurationProperties(SentinelProperties.class)
@Slf4j
public class SentinelAutoConfiguration {

    private static final String EXCLUDE_URLS = "spring.cloud.sentinel.excludeUrls";

    @Bean
    @ConditionalOnProperty(name = {"spring.cloud.sentinel.filter.enabled"}, havingValue = "false")
    @ConditionalOnBean(SentinelProperties.class)
    public FilterRegistrationBean sentinelFilter(SentinelProperties properties, SentinelCommonFilter sentinelCommonFilter, ApplicationContext applicationContext) {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean();
        com.alibaba.cloud.sentinel.SentinelProperties.Filter filterConfig = properties.getFilter();
        if (filterConfig.getUrlPatterns() == null || filterConfig.getUrlPatterns().isEmpty()) {
            List<String> defaultPatterns = new ArrayList<>();
            defaultPatterns.add("/*");
            filterConfig.setUrlPatterns(defaultPatterns);
        }
        registration.addUrlPatterns(filterConfig.getUrlPatterns().toArray(new String[0]));
        registration.setFilter(sentinelCommonFilter);
        Map<String, String> parameters = new HashMap<>();
        parameters.put(SentinelCommonFilter.HTTP_METHOD_SPECIFY, "false");
        parameters.put(EXCLUDE_URLS, applicationContext.getEnvironment().getProperty(EXCLUDE_URLS, ""));
        registration.setInitParameters(parameters);
        registration.setOrder(filterConfig.getOrder());
        log.info("[Sentinel Starter] register Sentinel with urlPatterns: {}.", filterConfig.getUrlPatterns());
        return registration;
    }

    @Bean
    public SentinelCommonFilter springCommonFilter(ApplicationContext applicationContext) {

        return new SentinelCommonFilter();
    }

}