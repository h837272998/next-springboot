package com.hjh.myspringboot.myspringboot.web.config;

import com.hjh.myspringboot.myspringboot.web.filter.TimeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: HJH
 * @Date: 2019-08-17 19:19
 */
//@Configuration
public class WebConfig {
    @Bean
    public FilterRegistrationBean timeFilter(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        TimeFilter timeFilter = new TimeFilter();
        registrationBean.setFilter(timeFilter);

        List<String> urls = new ArrayList<>();
        urls.add("/*");  //拦截url
        registrationBean.setUrlPatterns(urls);
        return registrationBean;
    }
}
