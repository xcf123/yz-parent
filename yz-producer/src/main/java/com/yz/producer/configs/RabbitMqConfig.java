package com.yz.producer.configs;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMqConfig
{
    @Value("${queueName}")
    private String queueName;

    @Value("${topicName}")
    private String topicName;

    public final static String FANOUTNAME = "publish.fanout";



    public static final String DLX_EXCHANGE_NAME = "dlx.exchange.name";
    public static final String DLX_QUEUE_NAME = "dlx.queue.name";
    public static final String DLX_ROUTING_KEY = "dlx.routing.key";


    @Bean
    Queue testdlxqueue() {
        Map<String, Object> args = new HashMap<>();
        //设置消息过期时间
        args.put("x-message-ttl", 10000);
        //设置死信交换机
        args.put("x-dead-letter-exchange", DLX_EXCHANGE_NAME);
        //设置死信 routing_key
        args.put("x-dead-letter-routing-key", DLX_ROUTING_KEY);
        return new Queue("publish.test.dlx", true, false, false, args);
    }

    /**
     * 普通交换机
     * @return
     */
    @Bean
    DirectExchange directExchange() {
        return new DirectExchange("publish.direct", true, false);
    }
    @Bean
    Binding directBinding() {
        return BindingBuilder.bind(testdlxqueue()).to(directExchange()).with("direct.routing.key");
    }



    @Bean
    public Queue dlxQueue() {
        return new Queue(DLX_QUEUE_NAME,true);
    }


    @Bean
    DirectExchange dlxExchange() {
        return new DirectExchange(DLX_EXCHANGE_NAME,true,false);
    }


    /**
     * 绑定死信队列和死信交换机
     * @return
     */
    @Bean
    Binding dlxBinding() {
        return BindingBuilder.bind(dlxQueue()).to(dlxExchange()).with(DLX_ROUTING_KEY);
    }



    @Bean
    public Queue topicQueue() {
        return new Queue(topicName,true);
    }

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(topicName,true,false);
    }

    @Bean
    Binding bindingTopicExchangeMessage() {
        return BindingBuilder.bind(topicQueue()).to(topicExchange()).with("publish");
    }

    @Bean
    Queue test() {
        return new Queue("publish.test");
    }

    @Bean
    Queue xiaomi() {
        return new Queue("xiaomi");
    }
    @Bean
    Queue huawei() {
        return new Queue("huawei");
    }
    @Bean
    Queue phone() {
        return new Queue("phone");
    }
    @Bean
    Binding xiaomiBinding() {
        return BindingBuilder.bind(xiaomi()).to(topicExchange())
                .with("xiaomi.#");
    }
    @Bean
    Binding huaweiBinding() {
        return BindingBuilder.bind(huawei()).to(topicExchange())
                .with("huawei.#");
    }
    @Bean
    Binding phoneBinding() {
        return BindingBuilder.bind(phone()).to(topicExchange())
                .with("#.phone.#");
    }


    @Bean
    public Queue queuettl() {

        Map<String, Object> args = new HashMap<>();
        args.put("x-message-ttl", 10000);
        return new Queue("ttl.queue", true, false, false, args);

    }


    @Bean
    public Queue queue() {
        return new Queue(queueName,true);
    }

    @Bean
    Queue queueOne() {
        return new Queue("queue.one");
    }
    @Bean
    Queue queueTwo() {
        return new Queue("queue.two");
    }
    @Bean
    Binding bindingOne() {
        return BindingBuilder.bind(queueOne()).to(fanoutExchange());
    }
    @Bean
    Binding bindingTwo() {
        return BindingBuilder.bind(queueTwo()).to(fanoutExchange());
    }

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUTNAME, true, false);
    }




    @Bean
    public MessagePostProcessor messagePostProcessor(){
        return  new MessagePostProcessor()
        {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException
            {
                message.getMessageProperties().setExpiration("5000");
                return message;
            }
        };
    }
}
