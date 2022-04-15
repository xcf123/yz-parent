package com.yz.consumerb.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class HelloReceiver {

    @RabbitListener(queues = "user.order.queue")
    public void process(String message) {
        System.out.println(message+" | "+ LocalDateTime.now());
    }



}