package com.yuanzong.beans.testBean;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author xiechaofeng
 * @date 2018/7/20 9:18
 * @desc
 */
@RunWith(Parameterized.class)
public class CalculatorTest
{
    private static Calculator calculator=new Calculator();
    private int result;
    private int param;

    @Parameterized.Parameters
   public static Collection data(){
        List<Object[]> list = Arrays.asList(new Object[][]{{2, 4}, {0, 0}, {-3, 9}});
        return list;
    }

    public CalculatorTest(int param,int result){
        this.param=param;
        this.result=result;
    }

    @Test
    public void square(){
        calculator.square(param);
        assertEquals(result,calculator.getResult());
    }
}