package com.yuanzong.controller;

import com.yuanzong.beans.*;
import com.yuanzong.utils.HtmlUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiechaofeng
 * @date 2018/7/10 14:41
 * @desc
 */
public class TestDict
{
    public static void main(String[] args)
    {
        try
        {
            File file = new File("D:\\Download\\2.xml");
            List<String> utf8 = FileUtils.readLines(file, "utf8");
            for (String s : utf8)
            {
                if (!s.startsWith("<entry"))
                {
                    continue;
                }
                s = HtmlUtils.unespace(s);
                System.out.println(s);
                ParseBean parseBean = new ParseBean();
                //读取xml文件到Document中
                Document doc = DocumentHelper.parseText(s);
                //获取xml文件的根节点
                Element rootElement = doc.getRootElement();
                List<ExplanationBean> explanationBeanList = new ArrayList<>();

                //读取单词名和词性
                String name = rootElement.element("h").getTextTrim();
                Element p = rootElement.element("p");
                parseBean.name = name;
                if (p != null)
                {
                    parseBean.partOfSpeech = p.attributeValue("p");
                }

                List<Element> explanationList = rootElement.elements("n-g");
                List<Element> elements = rootElement.elements("sl-g");

                if (explanationList == null || explanationList.size() == 0)
                {
                    ExplanationBean explanationBean = new ExplanationBean();
                    fillBaseBean(elements, explanationBean);
                    explanationBeanList.add(explanationBean);
                }
                else
                {
                    for (Element node : explanationList)
                    {
                        ExplanationBean explanationBean = new ExplanationBean();
                        Element d = node.element("d");
                        //设置短语
                        if (d != null)
                        {
                            String englishPhrase = d.getTextTrim();
                            String chinesePhrase = d.element("chn").getTextTrim();
                            explanationBean.englishPhrase = englishPhrase;
                            explanationBean.chinesePhrase = chinesePhrase;
                        }

                        List<Element> pareseNodeList = node.elements("sl-g");
                        //找出来sl-g
                        fillBaseBean(pareseNodeList,explanationBean);
                        explanationBeanList.add(explanationBean);
                    }
                    parseBean.explanationBeanList = explanationBeanList;
                }
                Element xr = rootElement.element("xr");
                if(xr !=null){
                    parseBean.note = xr.element("xh").getTextTrim();
                }
                Element vsG = rootElement.element("vs-g");
                if(vsG !=null){
                    Element v1 = vsG.element("v");
                    if(v1!=null){
                        parseBean.shaped = v1.getTextTrim();
                    }
                }

                HtmlUtils.recordInsertSql(parseBean);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


        try
        {
            FileUtils.writeLines(new File("D:\\Download\\3.sql"), HtmlUtils.sql,false);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public static void fillBaseBean(List<Element> elements, ExplanationBean explanationBean)
    {
        List<BaseBean> baseBeanList = new ArrayList<>();
        for (Element base2 : elements)
        {
            BaseBean baseBean = new BaseBean();
            //单个sl-g的词性
            String sl = base2.attribute("sl").getValue();
            baseBean.speech = sl;

            List<SpeechDetailBean> speechDetailBeans = new ArrayList<>();
            List<Element> speechDetailNode = base2.elements("sb-g");
            //找出sb-g
            for (Element speechDetail : speechDetailNode)
            {
                List<Element> cl = speechDetail.elements("cl");
                SpeechDetailBean speechDetailBean = new SpeechDetailBean();
                List<String> englishExplanation = new ArrayList<>();
                String chineseExplanation = "";
                List<Section> sectionList = new ArrayList<>();
                for (Element clElement : cl)
                {
                    Map<String, String> exampleExplanation = new HashMap<>();
                    String text = clElement.getTextTrim();
                    englishExplanation.add(text);
                    Element chn = clElement.element("chn");
                    if (chn != null)
                    {
                        chineseExplanation = chn.getTextTrim();
                    }
                }

                List<Element> x = speechDetail.elements("x");
                for (Element element : x)
                {
                    Section section = new Section();
                    String english = element.getTextTrim();
                    section.english = english;

                    Element chn = element.element("chn");
                    if(chn!=null){
                        String chinese =  chn.getTextTrim();
                        section.chinese = chinese;
                    }

                    sectionList.add(section);
                }
                speechDetailBean.englishExplanation = englishExplanation;
                speechDetailBean.chineseExplanation = chineseExplanation;
                speechDetailBean.sectionList = sectionList;
                speechDetailBeans.add(speechDetailBean);
            }
            baseBean.speechDetailBeans = speechDetailBeans;
            baseBeanList.add(baseBean);
        }
        explanationBean.baseBeanList = baseBeanList;
    }
}


