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
 * @Date: 2019-08-18 18:21
 */
@Slf4j
@Component
@RabbitListener(queues = "order")
public class OrderReceiver {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitHandler
    public void process(String re) {

        new Thread(()->{
            log.info("3.监听到下单请求："+re);
            //处理2s
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //处理完成
            log.info("4.订单处理完成："+re);
            rabbitTemplate.convertAndSend("finish",re);
        }).start();
    }
}
