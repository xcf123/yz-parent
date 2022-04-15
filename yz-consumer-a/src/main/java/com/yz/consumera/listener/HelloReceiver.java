package com.yz.consumera.listener;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class HelloReceiver
{

    @Value("${queueName}")
    private String queueName;

    @Value("${topicName}")
    private String topicName;

//    @RabbitListener(queues = "publish.queue")
    public void  receive(String text){
        System.out.println("QueueListener: consumer-a 收到一条信息: " + text+Thread.currentThread().getName());
    }
//    @RabbitListener(queues = "publish.queue",concurrency = "10")
    public void  receive2(String text){
        System.out.println("QueueListener22222: consumer-a 收到一条信息: " + text+Thread.currentThread().getName());
    }

    //手动ack
//    @RabbitListener(queues = "publish.queue")
    public void receive3(Message message, Channel channel) throws IOException {
        System.out.println("receive="+message.getBody());
        channel.basicAck(((Long) message.getMessageProperties().getDeliveryTag()),true);
    }

//    @RabbitListener(queues = "publish.queue",concurrency = "10")
    public void receive4(Message message, Channel channel) throws IOException {
        System.out.println("receive2 = " + message.getBody() + "------->" + Thread.currentThread().getName());
        channel.basicReject(((Long) message.getMessageProperties().getDeliveryTag()), true);
    }


    @RabbitListener(queues = "publish.queue")
    public void  receiveConfirm(Message message, Channel channel) throws IOException {
            try {
                log.info("小富收到消息：{}", message);

                //TODO 具体业务
                int i=1/0;
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);

            }  catch (Exception e) {
                log.info("Redelivered"+message.getMessageProperties().getRedelivered());
                if (message.getMessageProperties().getRedelivered()) {
                    log.error("消息已重复处理失败,拒绝再次接收...");
                    channel.basicReject(message.getMessageProperties().getDeliveryTag(), false); // 拒绝消息
                } else {
                    log.error("消息即将再次返回队列处理...");
                    channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
                }
            }
    }

    @RabbitListener(queues = "publish.topic")
    public void receiveTopic(String text){

        System.out.println("TopicListener: consumer-a 收到一条信息: " + text);
    }


}