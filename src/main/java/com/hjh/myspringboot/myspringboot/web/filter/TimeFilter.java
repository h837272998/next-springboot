package com.hjh.myspringboot.myspringboot.web.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * @Description:
 * @Author: HJH
 * @Date: 2019-08-17 18:55
 */
@Slf4j
//@Component
public class TimeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("TimeFilter Init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("time filter start");
        long begin = System.currentTimeMillis();
        filterChain.doFilter(servletRequest,servletResponse);
        log.info("the request spent :"+(System.currentTimeMillis()-begin));
        log.info("time filter finish");
    }

    @Override
    public void destroy() {
        log.info("TimeFilter Destroy");
    }
}
