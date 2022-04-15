package com.yz.consumera.configs;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig
{
    @Value("${queueName}")
    private String queueName;

    @Bean
    public Queue Queue() {
        return new Queue(queueName);
    }
}
