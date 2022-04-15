package com.yuanzong.utils;

import com.yuanzong.beans.*;
import com.yuanzong.beans.SchoolBeans.SchoolBean;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class SchoolUtils
{
    public static  List<String> sql = new ArrayList<>(1024);

    public static String recordInsertSql(SchoolBean bean)
    {
        if (bean == null)
        {
            return "";
        }
//        System.out.println(html);
        String sql =  "insert into tb_school (`schoolName`,`province`,`city`,`area`,`type`)" +
                "values ('" + bean.schoolName +"','" +bean.province+ "','"+bean.city+"','"+bean.area+"'" +
                ",'"+bean.type+"');";
        SchoolUtils.sql.add(sql);
        return sql;
    }


}
