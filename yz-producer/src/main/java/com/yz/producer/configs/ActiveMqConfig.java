package com.yz.producer.configs;


import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import javax.jms.Queue;
import javax.jms.Topic;


//@Configuration
public class ActiveMqConfig
{
    @Value("${queueName}")
    private String queueName;

    @Value("${topicName}")
    private String topicName;

    @Value("${spring.activemq.user}")
    private String usrName;

    @Value("${spring.activemq.password}")
    private  String password;

    @Value("${spring.activemq.broker-url}")
    private  String brokerUrl;

    @Bean
    public Queue queue(){
        return new ActiveMQQueue(queueName);
    }

    @Bean
    public Topic topic(){
        return new ActiveMQTopic(topicName);
    }

//    @Bean
//    public ActiveMQConnectionFactory connectionFactory() {
//        return new ActiveMQConnectionFactory(usrName, password, brokerUrl);
//    }

//    @Bean
//    public JmsListenerContainerFactory<?> jmsListenerContainerQueue(ActiveMQConnectionFactory connectionFactory){
//        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
//        bean.setConnectionFactory(connectionFactory);
//        return bean;
//    }
//
//    @Bean
//    public JmsListenerContainerFactory<?> jmsListenerContainerTopic(ActiveMQConnectionFactory connectionFactory){
//        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
//        //设置为发布订阅方式, 默认情况下使用的生产消费者方式
//        bean.setPubSubDomain(true);
//        bean.setConnectionFactory(connectionFactory);
//        return bean;
//    }

}
