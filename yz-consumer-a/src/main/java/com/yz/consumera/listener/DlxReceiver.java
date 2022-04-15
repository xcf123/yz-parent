package com.yz.consumera.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DlxReceiver {
    @RabbitListener(queues = "dlx.queue.name")
    public void handler1(String message) {
        System.out.println("dlxConsumer:" + message);
        System.out.println(System.currentTimeMillis());
    }

}
