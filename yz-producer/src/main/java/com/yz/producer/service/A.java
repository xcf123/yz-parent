package com.yz.producer.service;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 描述
 *
 * @author: xiechaofeng
 * @since: 2022/4/6
 */
@Component
public class A implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("启动方法");
    }
}
