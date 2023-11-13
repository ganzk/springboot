package com.config;

import com.filter.TrackFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置filter的Config类
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<TrackFilter> filterFilterRegistrationBean(){
        FilterRegistrationBean<TrackFilter> fiter = new FilterRegistrationBean<>();
        fiter.setFilter(new TrackFilter());
        fiter.addUrlPatterns("/*");
//        fiter.setOrder(0);  //设置优先级
        return fiter;
    }

}
