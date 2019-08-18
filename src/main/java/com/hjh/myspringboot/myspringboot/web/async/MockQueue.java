package com.hjh.myspringboot.myspringboot.web.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Description:应用2 。获得消息队列并处理。
 * @Author: HJH
 * @Date: 2019-08-18 13:18
 */
@Slf4j
@Component
public class MockQueue {

    //下单
    private String placeOrder;

    //订单完成
    private String completeOrder;

    public String getPlaceOrder() {
        return placeOrder;
    }

    public void setPlaceOrder(String placeOrder) throws InterruptedException {
        new Thread(()->{
            log.info("接收下单请求："+placeOrder);
            //处理2s
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //处理完成
            this.completeOrder = placeOrder;
            log.info("下单请求处理完成："+placeOrder);
        }).start();


    }

    public String getCompleteOrder() {
        return completeOrder;
    }

    public void setCompleteOrder(String completeOrder) {
        this.completeOrder = completeOrder;
    }
}
