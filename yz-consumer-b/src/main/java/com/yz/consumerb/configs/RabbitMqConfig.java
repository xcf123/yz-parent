package com.yz.consumerb.configs;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMqConfig
{
    @Value("${queueName}")
    private String queueName;

    @Value("${topicName}")
    private String topicName;

    @Bean
    public Queue queue() {
        return new Queue("user.order.queue",true);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange("user.order.exchange");
    }

    @Bean
    Binding bindingExchangeMessage2() {
        return BindingBuilder.bind(queue()).to(exchange()).with("order");
    }
}
