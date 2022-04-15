package com.yz.dubbocomsumer.client;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yz.provider.service.TestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dubbo")
public class TestClient
{
    @Reference(version = "1.0.0")
    private static TestService testService;

    @RequestMapping("message")
    public String dubboTest(){
        String s = testService.showMessage();
        return s;
    }

    @RequestMapping("test")
    public String test(){
        System.out.println(1);
        return "111";
    }


}
