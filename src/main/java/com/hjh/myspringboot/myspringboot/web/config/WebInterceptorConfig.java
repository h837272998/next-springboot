package com.hjh.myspringboot.myspringboot.web.config;

import com.hjh.myspringboot.myspringboot.web.interceptor.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description:
 * @Author: HJH
 * @Date: 2019-08-17 19:40
 */
//@Configuration
public class WebInterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private TimeInterceptor timeInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timeInterceptor);
    }
}
