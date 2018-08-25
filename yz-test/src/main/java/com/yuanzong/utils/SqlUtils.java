package com.yuanzong.utils;


import com.yuanzong.beans.cultureBeans.CChineseBaseBean;
import com.yuanzong.beans.cultureBeans.CEnglishBaseBean;
import com.yuanzong.beans.cultureBeans.CParseBean;
import com.yuanzong.beans.cultureBeans.CSection;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class SqlUtils
{
    public static  List<String> sql = new ArrayList<>(1024);



    public static String recordInsertSql(String key,String value)
    {
        if (key == null)
        {
            return "";
        }
        System.out.println(key +"----"+value);
        String sql =  "insert into dict_chinese(`key`,`value`)values ('" + key.replace("'",".").toLowerCase() +"','" +value.replace("'",".")+ "')"+";";
        SqlUtils.sql.add(sql);
        return sql;
    }




}
