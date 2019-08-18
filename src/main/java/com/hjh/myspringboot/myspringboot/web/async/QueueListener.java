package com.hjh.myspringboot.myspringboot.web.async;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @Description:模拟消息队列。ContextRefreshedEvent容器初始化就监听
 * @Author: HJH
 * @Date: 2019-08-18 13:33
 */
@Component
@Slf4j
public class QueueListener implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        new Thread(()->{
            while (true){
                if (!StringUtils.isBlank(mockQueue.getCompleteOrder())){
                    String orderId = mockQueue.getCompleteOrder();
                    log.info("返回订单处理结果："+orderId);
                    deferredResultHolder.getMap().get(orderId).setResult("place order success");
                    mockQueue.setCompleteOrder(null);
                }else {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();


    }
}
