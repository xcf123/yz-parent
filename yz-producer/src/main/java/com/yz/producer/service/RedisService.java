package com.yz.producer.service;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisService {
    @Autowired
    private RedisTemplate redisTemplate;

    public void readTask(){
        ListOperations ops = redisTemplate.opsForList();
        while (true){
            Object ob = ops.rightPop("taskList", 5, TimeUnit.SECONDS);
            System.out.println("taskConsumer"+ JSON.toJSONString(ob));
        }
    }

    public void addTask(String msg){
        ListOperations ops = redisTemplate.opsForList();
        ops.leftPush("taskList",msg);
    }
}
