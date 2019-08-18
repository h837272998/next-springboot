package com.hjh.myspringboot.myspringboot.web.config;

import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author: HJH
 * @Date: 2019-08-18 19:10
 */
@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue queue1(){
        return new Queue("order");
    }

    @Bean
    public Queue queue2(){
        return new Queue("finish");
    }
}
