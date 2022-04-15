package com.yuanzong.controller;

import java.util.PrimitiveIterator;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xiechaofeng
 * @date 2018/7/18 9:21
 * @desc
 */
public class TestThread implements Runnable
{
    private static  boolean flag = true ;
    @Override
    public void run()
    {
        while (flag){
            System.out.println(Thread.currentThread().getName()+"正在执行");
        }
        System.out.println(Thread.currentThread().getName()+"执行完毕");
    }

    public static void main22(String[] args) throws InterruptedException
    {
        TestThread te=new TestThread();
        new Thread(te,"threadA").start();
        System.out.println("main 线程正在执行");
        TimeUnit.MILLISECONDS.sleep(100) ;
        te.stopThread();

    }
    private void stopThread(){
        flag = false ;
    }

    public static void main(String[] args)
    {
        String  s="VALUES ('Actors' Studio')";
        s=s.replace("'","")
                 .replace("ˌ","");
        System.out.println(s);
    }
}
