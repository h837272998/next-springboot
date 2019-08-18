package com.hjh.myspringboot.myspringboot.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @Description:
 * @Author: HJH
 * @Date: 2019-08-17 19:31
 */
@Slf4j
@Component
public class TimeInterceptor implements HandlerInterceptor {

    /**
     * @Description:控制器方法调用之前
     * @Author: HJH
     * @Date: 2019-08-17 19:35
     * @Param: [request, response, handler]
     * @Return: boolean
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        log.info("Interceptor perHandle");
//        HandlerMethod handlerMethod = (HandlerMethod) handler;

//        log.info("Interceptor Class Name: "+((HandlerMethod)handler).getBean().getClass().getName());
//        log.info("Interceptor Method Name: "+((HandlerMethod)handler).getMethod().getName());
        request.setAttribute("startTime",System.currentTimeMillis());
        return true;
    }
    /**
     * @Description:控制器完成之后。但当控制器抛出错误时就不会进入该函数
     * @Author: HJH
     * @Date: 2019-08-17 19:36
     * @Param: [request, response, handler, modelAndView]
     * @Return: void
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           @Nullable ModelAndView modelAndView) throws Exception {
        log.info("Interceptor postHandle");
        long start = (long) request.getAttribute("startTime");
        log.info("Time Interceptor Spent："+(System.currentTimeMillis()-start));
    }

    /**
     * @Description:类似try-catch 的finally
     * @Author: HJH
     * @Date: 2019-08-17 19:37
     * @Param: [request, response, handler, ex]
     * @Return: void
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                @Nullable Exception ex) throws Exception {
        log.info("Interceptor afterCompletion");
        long start = (long) request.getAttribute("startTime");
        log.info("Time Interceptor Spent："+(System.currentTimeMillis()-start));
        log.info("Exception is "+ex);
    }
}
