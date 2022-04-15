package com.yuanzong.controller;

import com.yuanzong.utils.HtmlUtils;
import com.yuanzong.utils.SqlUtils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.time.temporal.ValueRange;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xiechaofeng
 * @date 2018/7/16 16:28
 * @desc
 */
public class TestMdxDict {
    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\xcf\\Desktop\\汉语大词典.txt");
        List<String> utf8 = FileUtils.readLines(file, "utf8");
        LinkedList<String> objects = new LinkedList<>();
        objects.addAll(utf8);
        int j=0;
        while (!objects.isEmpty()){
            String s = objects.removeFirst();
            int i = s.indexOf("<font ");
            if(i==-1){
               j++;
                continue;
            }
            String key = s.substring(0, i);

            String html = s.substring(i, s.length());
            html=html.replace("\\n","");
            html=html.replaceAll("</ol>","</ol><br/>");
            html=html.replace("<link href=\"ahd3e.css\" rel=\"stylesheet\" />","");

            SqlUtils.recordInsertSql(key.trim(),html);
        }
        System.out.println(j);
        try
        {
            FileUtils.writeLines(new File("C:\\Users\\xcf\\Desktop\\chinese.sql"), SqlUtils.sql,false);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
