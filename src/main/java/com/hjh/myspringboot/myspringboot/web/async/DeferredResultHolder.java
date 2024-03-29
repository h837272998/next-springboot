package com.hjh.myspringboot.myspringboot.web.async;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.HashMap;
import java.util.Map;


/**
 * @Description:
 * @Author: HJH
 * @Date: 2019-08-18 13:23
 */
@Component
public class DeferredResultHolder {
    private Map<String , DeferredResult<String>> map = new HashMap<>();

    public Map<String, DeferredResult<String>> getMap() {
        return map;
    }

    public void setMap(Map<String, DeferredResult<String>> map) {
        this.map = map;
    }
}
