package com.yuanzong.utils;


import com.yuanzong.beans.cultureBeans.CChineseBaseBean;
import com.yuanzong.beans.cultureBeans.CEnglishBaseBean;
import com.yuanzong.beans.cultureBeans.CParseBean;
import com.yuanzong.beans.cultureBeans.CSection;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/*
 * 用于拼接解释
 *
 <p><big>单词<big></p>
 <p>  <b>单词加粗</b>
 <font >音标 </font>
 <small>词性</small>
 <font >方括号 </font>
 <font ><b>1</b>    <b>全称</b>  （ds部分） 文本 <font face="serif"><i> 例句</i> </font>
 <b>2</b>详见部分
 </font>
 </p>

 <p>  <b>中文</b>

 <b>1</b>详见部分
 <b>1</b> <font> 文本  <font face="san-serif"> 英文</font>中文  <font face="serif"><i> 英文</i></font>中文
 </font>
 </p>
 */


public class HtmlUtils2
{
    public static  List<String> cSql = new ArrayList<>(1024);
    public static String getHtml(CParseBean word)
    {
        if (word == null)
        {
            return "";
        }
        StringBuilder p = new StringBuilder(1024);
        fillTitle(p, word);
        fillEnglish(p, word.cEnglishBaseBeanList);
        fillChinese(p, word);
        return p.toString();
    }


    public static String recordInsertSql(CParseBean word)
    {
        if (word == null)
        {
            return "";
        }
        String name = word.englishName;
        String html = getHtml(word);
        System.out.println(html);
        String sql =  "insert into dict_oxford_ogbac (`key`,`value`)values ('" + name.replace("'",".") +"','" +html.replace("'",".")+ "')"+";";
        HtmlUtils2.cSql.add(sql);
        return sql;
    }


    private static void fillEnglish(StringBuilder p, List<CEnglishBaseBean> cEnglishBaseBeanList)
    {
        if (cEnglishBaseBeanList != null)
        {
            for (int i = 0; i < cEnglishBaseBeanList.size(); i++)
            {
                CEnglishBaseBean cEnglishBaseBean = cEnglishBaseBeanList.get(i);
                if(cEnglishBaseBeanList.size() > 1){
                    p.append("<b>");
                    p.append("  "+i + 1+". ");
                    p.append("</b>");
                }
                if(org.apache.commons.lang3.StringUtils.isNotEmpty(cEnglishBaseBean.abbreviation)){
                    p.append("<b>");
                    p.append(" ("+cEnglishBaseBean.abbreviation+") ");
                    p.append("</b>");
                }
                if(cEnglishBaseBean.seeDetailList!=null && cEnglishBaseBean.seeDetailList.size()!=0){
                    p.append("⇨");
                    for (int z=0;z<cEnglishBaseBean.seeDetailList.size();z++) {
                        if(z+1!=cEnglishBaseBean.seeDetailList.size()){

                            p.append(cEnglishBaseBean.seeDetailList.get(z)+",");
                        }else{
                            p.append(cEnglishBaseBean.seeDetailList.get(z));
                        }
                    }
                }
                if(org.apache.commons.lang3.StringUtils.isNotEmpty(cEnglishBaseBean.brackets)){
                    p.append("(");
                    p.append(cEnglishBaseBean.brackets);
                    p.append(")");
                }
                if(org.apache.commons.lang3.StringUtils.isNotEmpty(cEnglishBaseBean.english)){
                    p.append(cEnglishBaseBean.english);
                }
                if(cEnglishBaseBean.exampleSentenceList!=null && cEnglishBaseBean.exampleSentenceList.size()!=0){
                    List<String> exampleSentenceList = cEnglishBaseBean.exampleSentenceList;
                    p.append(" : ");
                    for(int j=0;j<exampleSentenceList.size();j++) {
                        if (j != 0)
                        {
                            p.append("◇");
                        }
                        p.append(" <font face=\"serif\">");
                        p.append("<i>");
                        p.append(exampleSentenceList.get(j));
                        p.append("</i>");
                        p.append("</font>");
                    }
                }

            }
        }
        p.append("</p>");
    }

    private static void fillChinese(StringBuilder p,CParseBean word)
    {
        p.append("<p>");
        if(org.apache.commons.lang3.StringUtils.isNotEmpty(word.chineseName)){
            p.append(" <b>");
            p.append(word.chineseName+"  ");
            p.append("</b>");
        }
        List<CChineseBaseBean> cChineseBaseBeanList = word.cChineseBaseBeanList;
        if (cChineseBaseBeanList != null )
        {
            for (int i=0;i<cChineseBaseBeanList.size();i++) {
                CChineseBaseBean cChineseBaseBean = cChineseBaseBeanList.get(i);
                if(cChineseBaseBeanList.size() > 1){
                    p.append("<b>");
                    p.append("  "+i + 1+". ");
                    p.append("</b>");
                }
                if(cChineseBaseBean.chineseSeeDetailList!=null && cChineseBaseBean.chineseSeeDetailList.size()!=0){
                    p.append("⇨");
                    for (int z=0;z<cChineseBaseBean.chineseSeeDetailList.size();z++) {
                        if(z+1!=cChineseBaseBean.chineseSeeDetailList.size()){

                            p.append(cChineseBaseBean.chineseSeeDetailList.get(z)+",");
                        }else{
                            p.append(cChineseBaseBean.chineseSeeDetailList.get(z));
                        }
                    }
                }
                if(org.apache.commons.lang3.StringUtils.isNotEmpty(cChineseBaseBean.translation)){
                    p.append("<font>");
                    p.append(cChineseBaseBean.translation);
                }
                if(cChineseBaseBean.cSectionList!=null && cChineseBaseBean.cSectionList.size()!=0){
                    p.append(" : ");
                    for (CSection cSection : cChineseBaseBean.cSectionList) {
                        p.append("<font face=\"serif\">");
                        p.append("<i>");
                        p.append(cSection.english+"  ");
                        p.append("</i>");
                        p.append("</font>");
                        p.append(cSection.chinese);
                    }
                }
                p.append("</font>");
            }
        }
        p.append("</p>");
    }

    private static void fillTitle(StringBuilder p, CParseBean word)
    {
        p.append("<p>");
        p.append("<big>");
        p.append("<b>");
        p.append(word.englishName+"  ");
        p.append("</b>");
        p.append("</big>");
        p.append("</p>");

        p.append("<p>");
        p.append("<b>");
        p.append(word.englishName+"  ");
        p.append("</b>");
        if(word.phoneticSymbolList!=null && word.phoneticSymbolList.size()!=0){
            List<String> phoneticSymbolList = word.phoneticSymbolList;
            p.append("<font>/");
            for (int i=0;i<phoneticSymbolList.size();i++){
                String s=phoneticSymbolList.get(i);
                if(i==phoneticSymbolList.size()-1){
                    p.append(s);
                }else{
                    p.append(s+",");
                }
            }

            p.append("/</font>");
        }
        if (!StringUtils.isEmpty(word.partOfSpeech))
        {
            p.append("<small>");
            p.append(word.partOfSpeech);
            p.append("</small> ");
        }
        if(word.squareBracketsList!=null && word.squareBracketsList.size()!=0){
            p.append("<font>");
            p.append(word.squareBracketsList);
            p.append("</font> ");
        }

    }


}
