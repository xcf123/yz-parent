package com.yz.provider.service.Impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.yz.provider.service.TestService;


@Service(version = "1.0.0")
public class TestServiceImpl implements TestService
{
    @Override
    public String showMessage()
    {
        return "service has bean transfer";
    }
}
