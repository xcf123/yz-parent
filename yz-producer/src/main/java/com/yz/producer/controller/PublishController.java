package com.yz.producer.controller;

import com.yz.producer.configs.RabbitMqConfig;
import com.yz.producer.service.ConfirmCallbackService;
import com.yz.producer.service.ReturnCallbackService;
import com.yz.producer.utils.RedisUtil;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.UUID;


@RestController
@RequestMapping("/publish")
public class PublishController {

    @Value("${queueName}")
    private String queueName;

    @Value("${topicName}")
    private String topicName;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private ConfirmCallbackService confirmCallbackService;
    @Autowired
    private ReturnCallbackService returnCallbackService;

    @RequestMapping("/queue")
    public String queue(){

        for (int i = 0; i < 10 ; i++){
            template.convertAndSend(queueName,"queue"+i);
        }
        return "queue 发送成功";
    }

    @RequestMapping("/fanout")
    public void fanoutTest() {
        template.convertAndSend(RabbitMqConfig.FANOUTNAME,
                        null, "hello fanout!");
    }
    @RequestMapping("/topic")
    public void topicTest() {
        template.convertAndSend(topicName,
                "xiaomi.news","小米新闻..");
        template.convertAndSend(topicName,
                "huawei.news","华为新闻..");
        template.convertAndSend(topicName,
                "xiaomi.phone","小米手机..");
        template.convertAndSend(topicName,
                "huawei.phone","华为手机..");
        template.convertAndSend(topicName,
                "phone.news","手机新闻..");
    }

    @RequestMapping("/queuettl")
    public void queuettl() {
        template.convertAndSend("ttl.queue",
                null, "hello queuettl!");
    }

    @RequestMapping("/singlettl")
    public String singlettl(){
        Message message = MessageBuilder.withBody("hello javaboy".getBytes())
                .setExpiration("10000")
                .build();
        template.convertAndSend(queueName,message);
        return "queue 发送成功";
    }


    @RequestMapping("/testdlx")
    public void testdlx(){
        template.convertAndSend("publish.direct","direct.routing.key","send");
        System.out.println(System.currentTimeMillis());

    }

    @RequestMapping("/testCallback")
    public void testCallback(){
        /**
         * 确保消息发送失败后可以重新返回到队列中
         * 注意：yml需要配置 publisher-returns: true
         */
        template.setMandatory(true);

        template.setConfirmCallback(confirmCallbackService);
        template.setReturnCallback(returnCallbackService);


        template.convertAndSend(RabbitMqConfig.FANOUTNAME,null,"send",
                message -> {
                    message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                    return message;
                },
                new CorrelationData(UUID.randomUUID().toString()));
        System.out.println(System.currentTimeMillis());

    }

    //redis
    @RequestMapping("redis")
    public String redisShow(){

        ZSetOperations op = redisTemplate.opsForZSet();
        op.add("teacherScore3",88,3);
        op.add("teacherScore3",79,5);
        op.add("teacherScore3",90,1);
        Long teacherRank = op.rank("teacherScore3", 90);
        System.out.println("teacherRank"+teacherRank);
        Set teacherSet = op.range("teacherScore3", 0, 10);

        op.incrementScore("teacherScore3",22,2);
        Double teacherScore = op.incrementScore("teacherScore3", 22, 4);
        System.out.println("intersectAndStore3"+teacherScore);
        return "redis show success";
    }
}