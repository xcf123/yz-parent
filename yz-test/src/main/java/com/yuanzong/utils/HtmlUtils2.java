package com.yuanzong.utils;


import com.sun.javafx.scene.control.skin.VirtualFlow;
import com.yuanzong.beans.cultureBeans.*;

import org.springframework.util.StringUtils;

import java.awt.print.Paper;
import java.util.ArrayList;
import java.util.LinkedList;
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
        if(word.drgBean!=null){
            fillDrg(p,word.drgBean);
        }
        String s = p.toString();
        //替换文本中需要转义的加粗字体
        s=s.replace("|++","<b>");
        s=s.replace("++|","</b>");
        //替换上标
        s=s.replace("||+","<sup>");
        s=s.replace("+||","</sup>");
        //替换斜体
        s=s.replace("|--","<i>");
        s=s.replace("--|","</i>");
        return s;
    }

    private static void fillDrg(StringBuilder p, DrgBean drgBean)
    {
        p.append("<br>");
        p.append("<big>");
        p.append("<b>");
        p.append(drgBean.englishName+"&nbsp;&nbsp;");
        p.append("</b>");
        p.append("</big>");

        if (!StringUtils.isEmpty(drgBean.partOfSpeech))
        {
            p.append("<small><i>");
            p.append(drgBean.partOfSpeech);
            p.append("</i></small>&nbsp;");
        }
        if(drgBean.cEnglishBaseBean!=null){
            List<CEnglishBaseBean> list=new ArrayList<>();
            list.add(drgBean.cEnglishBaseBean);
            fillEnglish(p, list);
        }
        if(drgBean.cChineseBaseBean!=null){
            CChineseBaseBean cChineseBaseBean = drgBean.cChineseBaseBean;
            if(org.apache.commons.lang3.StringUtils.isNotBlank(cChineseBaseBean.name)){
                p.append("<b>");
                p.append("  "+cChineseBaseBean.name+". ");
                p.append("</b>&nbsp;&nbsp;");
            }

            if(org.apache.commons.lang3.StringUtils.isNotEmpty(cChineseBaseBean.translation)){
                p.append("<font>");
                p.append(cChineseBaseBean.translation);
                p.append("&nbsp;&nbsp;");
                p.append("</font>");
            }
            if(cChineseBaseBean.cSectionList!=null && cChineseBaseBean.cSectionList.size()!=0){
                p.append(" : ");
                for (CSection cSection : cChineseBaseBean.cSectionList) {
                    p.append("<font face=\"serif\">");
                    p.append("<i>");
                    p.append(cSection.english+"  ");
                    p.append("</i>");
                    p.append("</font>&nbsp;&nbsp;");
                    p.append(cSection.chinese);
                }
            }
            if(cChineseBaseBean.supplementList!=null && cChineseBaseBean.supplementList.size()!=0){
                List<String> chineseLyricsList = cChineseBaseBean.supplementList;
                p.append("<br>");
                for (String s : chineseLyricsList)
                {
                    p.append(s);
                    p.append("<br>");
                }
            }
            if(cChineseBaseBean.chineseSeeDetailList!=null && cChineseBaseBean.chineseSeeDetailList.size()!=0){
                p.append("⇨");

                if(cChineseBaseBean.chineseSeeDetailList.size()==1){
                    p.append("详见");
                    p.append(cChineseBaseBean.chineseSeeDetailList.get(cChineseBaseBean.chineseSeeDetailList.size()-1));
                }else{
                    for (int z=0;z<cChineseBaseBean.chineseSeeDetailList.size()-1;z++) {
                        p.append(cChineseBaseBean.chineseSeeDetailList.get(z)+",");

                    }
                    p.deleteCharAt(p.length()-1);
                    p.append("<br>详见");
                    p.append(cChineseBaseBean.chineseSeeDetailList.get(cChineseBaseBean.chineseSeeDetailList.size()-1));
                }


            }
        }

    }


    public static String recordInsertSql(CParseBean word)
    {
        if (word == null)
        {
            return "";
        }
        String name = word.englishName;
        name=name.replace("ˈ","")
                 .replace("'","")
                .replace("ˌ","")
                .replace("™","");
        String html = getHtml(word);
        System.out.println(html);
        String sql =  "insert into dict_oxford_ogbac (`key`,`value`)values ('" + name.replace("'","") +"','" +html.replace("'","''")+ "')"+";";
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
                    int num=i+1;
                    p.append("  "+num+". ");
                    p.append("</b>");
                }
                if(cEnglishBaseBean.squareBracketsList!=null &&cEnglishBaseBean.squareBracketsList.size()!=0){
                    p.append("[");
                    for (int j = 0; j < cEnglishBaseBean.squareBracketsList.size(); j++)
                    {
                        String s = cEnglishBaseBean.squareBracketsList.get(j);
                        if(s.equals("usupl"))
                        {
                            s="usu pl";
                        }else if(s.equals("obn")){
                            s="only before noun";
                        }else if(s.equals("singspv"))
                        {
                            s="sing+sing/pl v";
                        }
                        else if(s.equals("ubn"))
                        {
                            s="usu before noun";
                        }else {
                            s=s.toUpperCase();
                        }
                        if(j==0){
                            p.append(s);
                        }else {
                            p.append(","+s);
                        }


                    }
                    p.append("]&nbsp;&nbsp;");
                }
                if(org.apache.commons.lang3.StringUtils.isNotBlank(cEnglishBaseBean.annotation)){
                    p.append("<font face=\"serif\">");
                    p.append("<i>");
                    p.append(" ("+cEnglishBaseBean.annotation+") ");
                    p.append("</i>");
                    p.append("</font>&nbsp;&nbsp;");
                }
                if(org.apache.commons.lang3.StringUtils.isNotBlank(cEnglishBaseBean.entireName)){
                    p.append("("+cEnglishBaseBean.entireName+")&nbsp;&nbsp;");
                }
                if(org.apache.commons.lang3.StringUtils.isNotEmpty(cEnglishBaseBean.abbreviation)){
                    p.append("(");
                    p.append("<i>");
                    p.append("in full");
                    p.append("</i>&nbsp;");
                    p.append("<b>");
                    p.append(cEnglishBaseBean.abbreviation);
                    p.append("</b>");
                    p.append(")&nbsp;&nbsp;");
                }

                if(org.apache.commons.lang3.StringUtils.isNotEmpty(cEnglishBaseBean.brackets)){
                    p.append("(");
                    p.append(cEnglishBaseBean.brackets);
                    p.append(")&nbsp;&nbsp;");
                }
                if(org.apache.commons.lang3.StringUtils.isNotEmpty(cEnglishBaseBean.english)){
                    p.append(cEnglishBaseBean.english);
                    p.append("&nbsp;&nbsp;");
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
                        p.append("</font>&nbsp;&nbsp;");
                    }
                }
                if(cEnglishBaseBean.lyricsList!=null && cEnglishBaseBean.lyricsList.size()!=0){
                    List<String> lyricsList = cEnglishBaseBean.lyricsList;
                    p.append("<br>");
                    for (String s : lyricsList)
                    {
                        p.append(s);
                        p.append("<br>");
                    }
                }
                if(cEnglishBaseBean.seeDetailList!=null && cEnglishBaseBean.seeDetailList.size()!=0){
                    p.append("[");
                    if(cEnglishBaseBean.seeDetailList.size()==1){
                        p.append(cEnglishBaseBean.seeDetailList.get(0));
                    }else{
                        for (int z=0;z<cEnglishBaseBean.seeDetailList.size()-1;z++) {
                            p.append(cEnglishBaseBean.seeDetailList.get(z)+",");

                        }
                        p.deleteCharAt(p.length()-1);
                        p.append("<br>see");
                        p.append(cEnglishBaseBean.seeDetailList.get(cEnglishBaseBean.seeDetailList.size()-1));

                    }
                    p.append("&nbsp;&nbsp;");
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
                    int num=i+1;
                    p.append("  "+num+". ");
                    p.append("</b>");
                }
                if(org.apache.commons.lang3.StringUtils.isNotBlank(cChineseBaseBean.name)){
                    p.append("<b>");
                    p.append("  "+cChineseBaseBean.name+". ");
                    p.append("</b>&nbsp;&nbsp;");
                }

                if(org.apache.commons.lang3.StringUtils.isNotEmpty(cChineseBaseBean.translation)){
                    p.append("<font>");
                    p.append(cChineseBaseBean.translation);
                    p.append("&nbsp;&nbsp;");
                    p.append("</font>");
                }
                if(cChineseBaseBean.cSectionList!=null && cChineseBaseBean.cSectionList.size()!=0){
                    p.append(" : ");
                    for (CSection cSection : cChineseBaseBean.cSectionList) {
                        p.append("<font face=\"serif\">");
                        p.append("<i>");
                        p.append(cSection.english+"  ");
                        p.append("</i>");
                        p.append("</font>&nbsp;&nbsp;");
                        p.append(cSection.chinese);
                    }
                }
                if(cChineseBaseBean.supplementList!=null && cChineseBaseBean.supplementList.size()!=0){
                    List<String> chineseLyricsList = cChineseBaseBean.supplementList;
                    p.append("<br>");
                    for (String s : chineseLyricsList)
                    {
                        p.append(s);
                        p.append("<br>");
                    }
                }
                if(cChineseBaseBean.chineseSeeDetailList!=null && cChineseBaseBean.chineseSeeDetailList.size()!=0){
                    p.append("⇨");

                    if(cChineseBaseBean.chineseSeeDetailList.size()==1){
                        p.append("详见");
                        p.append(cChineseBaseBean.chineseSeeDetailList.get(cChineseBaseBean.chineseSeeDetailList.size()-1));
                    }else{
                        for (int z=0;z<cChineseBaseBean.chineseSeeDetailList.size()-1;z++) {
                            p.append(cChineseBaseBean.chineseSeeDetailList.get(z)+",");

                        }
                        p.deleteCharAt(p.length()-1);
                        p.append("<br>详见");
                        p.append(cChineseBaseBean.chineseSeeDetailList.get(cChineseBaseBean.chineseSeeDetailList.size()-1));
                    }


                }
            }
        }
        p.append("</p>");
    }

    private static void fillTitle(StringBuilder p, CParseBean word)
    {
        p.append("<p>");
        p.append("<big>");
        if(org.apache.commons.lang3.StringUtils.isNotBlank(word.beforeName)){
            p.append(word.beforeName+" ");
        }
        p.append("<b>");
        p.append(word.englishName+"  ");
        p.append("</b>");
        p.append("</big>");
        p.append("</p>");
        p.append("<p>");
        if(org.apache.commons.lang3.StringUtils.isNotBlank(word.beforeName)){
            p.append(word.beforeName+" ");
        }
        p.append("<b>");
        if(org.apache.commons.lang3.StringUtils.isNotBlank(word.flag)){
            p.append("<i>");
            p.append(word.englishName+"&nbsp;");
            p.append("</i>");
        }else {
            p.append(word.englishName+"&nbsp;");
        }
        p.append("</b>");

        if(org.apache.commons.lang3.StringUtils.isNotBlank(word.anotherNameExp)){
            p.append(word.anotherNameExp);
        }
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
            if(word.amPhoneticSymbolList!=null && word.amPhoneticSymbolList.size()!=0){
                p.append(";&nbsp;");
            }else{
                p.append("/</font>&nbsp;");
            }
        }
        if(word.amPhoneticSymbolList!=null && word.amPhoneticSymbolList.size()!=0){
            p.append("AmE&nbsp;");
            for (int i = 0; i < word.amPhoneticSymbolList.size(); i++)
            {
                String s=word.amPhoneticSymbolList.get(i);
                if(i==word.amPhoneticSymbolList.size()-1){
                    p.append(s);
                }else{
                    p.append(s+",");
                }

            }
            p.append("/</font>&nbsp;");
        }
        if (!StringUtils.isEmpty(word.partOfSpeech))
        {
            p.append("<small><i>");
            p.append(word.partOfSpeech);
            p.append("</i></small>&nbsp;");
        }
        if(!StringUtils.isEmpty(word.pronunciation)){
            p.append("<i>");
            p.append("("+word.pronunciation+")  ");
            p.append("</i>&nbsp;");
        }

        if(!StringUtils.isEmpty(word.ab)){
            p.append("( abbr&nbsp;");
            p.append("<b>");
            p.append(word.ab);
            p.append("</b>");
            p.append(")&nbsp;");
        }
        if(org.apache.commons.lang3.StringUtils.isNotBlank(word.anotherName)){
            p.append("("+word.anotherName+")&nbsp;");
        }
       /* if(word.squareBracketsList!=null && word.squareBracketsList.size()!=0){
            p.append("<font>");
            p.append(word.squareBracketsList);
            p.append("</font> ");
        }*/

    }

}
