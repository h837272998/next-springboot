package com.hjh.myspringboot.myspringboot.web.controller;

import com.hjh.myspringboot.myspringboot.web.async.DeferredResultHolder;
import com.hjh.myspringboot.myspringboot.web.async.MockQueue;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @Description:
 * @Author: HJH
 * @Date: 2019-08-17 22:47
 */
@Slf4j
@RestController
public class AsyncController {

    @GetMapping("async1")
    public Map sleep() throws InterruptedException {
        log.info("开始");
        for (int i=0;i<10;i++){
            Thread.sleep(1000);
        }
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("code","1");
        log.info("结束");
        return hashMap;
    }

    @GetMapping("async2")
    public Callable<String> async2(){
        log.info("主线程开始");
        Callable<String> result = new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("副线程开始");
                for (int i=0;i<5;i++){
                    Thread.sleep(1000);
                }
                log.info("副线程返回");
                return "success";
            }
        };
        log.info("主线程结束");
        return result;
    }

    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;

    @GetMapping("async3")
    public DeferredResult<String> async3() throws InterruptedException {
        log.info("主线程开始");
        String orderId = RandomStringUtils.randomNumeric(8);
        mockQueue.setPlaceOrder(orderId);
        DeferredResult<String> result = new DeferredResult();
        deferredResultHolder.getMap().put(orderId,result);
        log.info("主线程结束");
        return result;
    }

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @GetMapping("async4")
    public DeferredResult<String> async4() throws InterruptedException {
        log.info("主线程开始");
        String orderId = RandomStringUtils.randomNumeric(8);
        log.info("2.发送下单请求："+orderId);
        rabbitTemplate.convertAndSend("order",orderId);
        DeferredResult<String> result = new DeferredResult();
        deferredResultHolder.getMap().put(orderId,result);
        return result;
    }



}
