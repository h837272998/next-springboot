package com.hjh.myspringboot.myspringboot.web.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: HJH
 * @Date: 2019-08-18 18:33
 */
@Slf4j
@Component
@RabbitListener(queues = "finish")
public class OrderFinishReceiver {


    @Autowired
    private DeferredResultHolder deferredResultHolder;

    @RabbitHandler
    public void process(String re) {
        new Thread(()->{
            log.info("5.监听到完成的订单1："+re);
            deferredResultHolder.getMap().get(re).setResult("订单成功");
        }).start();
    }
}