package com.yuanzong.controller;

import com.yuanzong.beans.Person;
import org.junit.Test;

import java.lang.reflect.Field;

/**
 * @author xiechaofeng
 * @date 2018/8/17 11:22
 * @desc
 */
public class TestFiled
{
    @Test
    public void test() throws NoSuchFieldException
    {
        Person person=new Person();
        Field name = person.getClass().getDeclaredField("name");
        System.out.println("name:"+name);
    }


}
