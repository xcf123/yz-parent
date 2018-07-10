package com.yuanzong.moduls;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author xiechaofeng
 * @date 2018/7/10 11:11
 * @desc
 */
@Component
public class TestScheduled {
    @Scheduled(cron = "0 0/1 * * * ?")
    public void timerToNow(){
        System.out.println("now time:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }
}
