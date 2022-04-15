package com.yuanzong.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Game
{
    public static void main(String[] args)
    {
        String s="11";


        CyclicBarrier cyclicBarrier=new CyclicBarrier(4, new Runnable()
        {
            @Override
            public void run()
            {
                System.out.println("游戏开始");
            }
        });
        ExecutorService executor = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 4; i++)
        {
            executor.submit(new User(cyclicBarrier,i+"号选手"));
        }
        executor.shutdown();
    }
}
