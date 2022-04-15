package com.yz.dubbocomsumer.client.consumer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 客户端消费对象
 * @author Administrator
 */
public class ConsumerClient
{
	
    private ConsumerClient(){}
    
    private static ClassPathXmlApplicationContext context = null;
    
    /**
     * 获取dubbo服务客户端
     * @return
     */
    public static ClassPathXmlApplicationContext getConsumerClient()
    {
    	if(context == null)
    	{
    	   context = new ClassPathXmlApplicationContext(new String[] { "dubbo-client.xml" });
		   context.start();
    	}
    	
    	return context;
    }
}