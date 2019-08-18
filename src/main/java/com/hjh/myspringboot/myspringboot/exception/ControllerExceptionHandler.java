package com.hjh.myspringboot.myspringboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:全局控制器异常处理器
 * @Author: HJH
 * @Date: 2019-08-17 15:43
 */
@ControllerAdvice
public class ControllerExceptionHandler {


    /**
     * @Description:处理UserNotExistException,返回的是json对象
     * @Author: HJH
     * @Date: 2019-08-17 16:46
     * @Param: [ex]
     * @Return: java.util.Map<java.lang.String , java.lang.Object>
     */
    @ExceptionHandler(UserNotExistException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handleUserNotExistException(UserNotExistException ex) {
        Map<String, Object> result = new HashMap<>();
        result.put("id", ex.getId());
        result.put("message", ex.getMessage());
        return result;
    }

    /**
     * @Description:全局Exception处理。返回的是html页面
     * @Author: HJH
     * @Date: 2019-08-17 15:56
     * @Param: [req, e]
     * @Return: ModelAndView
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Object defaultErrorHandler(HttpServletRequest req, Exception ex) throws Exception {

        if ("application/json".equals(req.getHeader("Content-Type"))){
            Map<String, Object> result = new HashMap<>();
            result.put("message", ex.getMessage());
            return result;
        }else {
            ModelAndView mav = new ModelAndView();
            mav.addObject("exception", ex);
            mav.addObject("url", req.getRequestURL());
            mav.setViewName("error/errorPage");
            return mav;
        }
    }

}
