package com.yuanzong.controller;

import com.yuanzong.beans.*;
import com.yuanzong.utils.HtmlUtils;
import org.apache.commons.io.FileUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.StringBufferInputStream;
import java.io.StringReader;
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
                System.out.println(s);
                s = s.replace("&lsquo;","‘").replace("&rsquo;","’")
                .replace("&hellip;","…")
                .replace("&cw;","·")
                .replace("&mdash;","—")
                .replace("&ndash;","—")
                .replace("&dash2;","—")
                .replace("&thinsp;"," ")
                .replace("&dollar;","$")
                .replace("&pound;","£")
                .replace("&percnt;","%")
                .replace("&deg;","°")
                .replace("&eacute;","É")
                .replace("&trade;","™")
                .replace("&euro;","€")
                ;
                ParseBean parseBean = new ParseBean();
                //读取xml文件到Document中
                Document doc = DocumentHelper.parseText(s);
                //获取xml文件的根节点
                Element rootElement = doc.getRootElement();
                List<ExplanationBean> explanationBeanList = new ArrayList<>();

                //读取单词名和词性
                String name = rootElement.element("h").getTextTrim();
                String partOfSpeech = rootElement.element("p").attributeValue("p");
                parseBean.name = name;
                parseBean.partOfSpeech = partOfSpeech;

                List<Element> explanationList = rootElement.elements("n-g");
                for (Element node : explanationList)
                {
                    ExplanationBean explanationBean = new ExplanationBean();
                    List<BaseBean> baseBeanList = new ArrayList<>();
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
                    for (Element base : pareseNodeList)
                    {
                        BaseBean baseBean = new BaseBean();
                        //单个sl-g的词性
                        String sl = base.attribute("sl").getValue();
                        baseBean.speech = sl;

                        List<SpeechDetailBean> speechDetailBeans = new ArrayList<>();
                        List<Element> speechDetailNode = base.elements("sb-g");
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
                                String chinese = element.element("chn").getTextTrim();
                                section.english = english;
                                section.chinese = chinese;
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
                    explanationBeanList.add(explanationBean);
                }
                parseBean.explanationBeanList = explanationBeanList;


                System.out.println(HtmlUtils.getHtml(parseBean));
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}


