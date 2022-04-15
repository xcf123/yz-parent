package com.yz.producer.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class ConfirmCallbackService implements RabbitTemplate.ConfirmCallback {
    //消息只要被 rabbitmq broker 接收到就会触发 confirmCallback 回调 。
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            log.info("发送者已经收到确认，correlationData={} ,ack={}, cause={}", correlationData.getId(), ack, cause);
        } else {
            log.info("消息发送异常，correlationData={} ,ack={}, cause={}", correlationData.getId(), ack, cause);
        }

    }
}