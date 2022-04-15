package com.yuanzong.controller;

import com.fasterxml.jackson.core.JsonParser;
import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;
import org.junit.Before;

import java.text.MessageFormat;
import java.util.*;
import java.util.concurrent.ForkJoinPool;

import static org.junit.Assert.*;

/**
 * @author xiechaofeng
 * @date 2018/7/24 9:21
 * @desc
 */
public class Test
{
    private static final byte[] buffer = new byte[1024 * 10];
    private static long startTime;

    static
    {
        for (int i = 0; i < buffer.length; i++)
        {
            buffer[i] = (byte) (1);
        }
    }

    @org.junit.Test
    public void test1()
    {
        startTime = System.nanoTime();
        Arrays.copyOf(buffer, buffer.length);
        calcTime("copyOf");


        startTime = System.nanoTime();
        byte[] buffer2 = new byte[1024 * 10];
        for (int i = 0; i < buffer.length; i++)
        {
            buffer2[i] = buffer[i];
        }
        calcTime("forCopy");


        startTime = System.nanoTime();
        byte[] clone = buffer.clone();
        calcTime("clone");

        startTime = System.nanoTime();
        byte[] newBuffer4 = new byte[buffer.length];
        System.arraycopy(buffer, 0, newBuffer4, 0, buffer.length);
        calcTime("systemArraycopy");

        for (int i = 0; i < buffer.length; i++)
        {
            buffer[i] = buffer[2];
        }
        System.out.println(newBuffer4[1]);
    }
//得出结论效率 clone>copyOf> arrayCopy>for循环copy

    private static void calcTime(String type)
    {
        long endTime = System.nanoTime();
        System.out.println(type + " cost " + (endTime - startTime) + " nanosecond");
    }

    @org.junit.Test
    public void test2()
    {
        Object[] objects=new Object[1024];
        UserBean userBean=new UserBean();
        for(int i=0;i<objects.length;i++){
            objects[i]=userBean;
        }
        System.out.println("1----------"+objects[1]);

        Object[] newBuffer4 = new Object[objects.length];
        System.arraycopy(objects, 0, newBuffer4, 0, objects.length);
        System.out.println("2-----------"+newBuffer4[1]);

        for (int i = 0; i < objects.length; i++)
        {
            UserBean userBean2=new UserBean();
            objects[i] = userBean2;
        }
        System.out.println("3------------"+objects[1]);
        System.out.println("4------------"+newBuffer4[1]);

    }

    @org.junit.Test
    public void test3(){
        String message = MessageFormat.format(">$teacher|openStep|&|'{'classId:{0},lessonId:{1},stepId:{2}'}'",
                "1","2","3");
        System.out.println(message);

    }
}


class UserBean{
    public String username;
    public String password;
}