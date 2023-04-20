package com.woniuxy.mall.config;

import com.woniuxy.mall.filter.AuthFilter;
import com.woniuxy.mall.filter.CorsFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {
    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilterRegistrationBean(){
        FilterRegistrationBean<CorsFilter>filterFilterRegistrationBean=new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(corsFilter());
        filterFilterRegistrationBean.addUrlPatterns("/*");
        filterFilterRegistrationBean.setOrder(0);
        return filterFilterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<AuthFilter> authFilterRegistrationBean(){
        FilterRegistrationBean<AuthFilter>filterFilterRegistrationBean=new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(authFilter());
        filterFilterRegistrationBean.addUrlPatterns("/*");
        filterFilterRegistrationBean.setOrder(1);
        return filterFilterRegistrationBean;
    }
    @Bean
    public CorsFilter corsFilter(){
        return new CorsFilter();
    }
    @Bean
    public AuthFilter authFilter(){
        return new AuthFilter();
    }
}
