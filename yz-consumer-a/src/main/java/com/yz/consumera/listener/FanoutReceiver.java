package com.yz.consumera.listener;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class FanoutReceiver
{

    @RabbitListener(queues = "queue.one")
    public void receive3(String message) throws IOException {
        System.out.println("FanoutReceiver:handler1:" + message);
    }

    @RabbitListener(queues = "queue.two",concurrency = "10")
    public void receive4(String message) throws IOException {
        System.out.println("FanoutReceiver:handler2:" + message);
    }





}