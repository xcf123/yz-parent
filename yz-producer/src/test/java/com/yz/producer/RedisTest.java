package com.yz.producer;

import com.yz.producer.service.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 描述
 *
 * @author: xiechaofeng
 * @since: 2022/4/2
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ProducerApplication.class})
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedisService redisService;

    @Test
    public void  test() throws InterruptedException {
        for (int i = 0; i < 2; i++) {
            redisService.addTask(i+"------"+System.currentTimeMillis());
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                redisService.readTask();
            }
        }).start();


        Thread.sleep(100*1000);
        System.out.println("睡眠100秒");
        for (int i = 3; i < 6; i++) {
            redisService.addTask(i+"------"+System.currentTimeMillis());
        }
    }

}
