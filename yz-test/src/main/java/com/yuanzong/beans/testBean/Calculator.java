package com.yuanzong.beans.testBean;

/**
 * @author xiechaofeng
 * @date 2018/7/20 9:16
 * @desc
 */
public class Calculator
{
    private static int result;

    public void square(int a){
        result= Math.abs(a)*Math.abs(a);
    }

    public static int getResult()
    {
        return result;
    }
}
