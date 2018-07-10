package com.yuanzong.controller;

import com.yuanzong.beans.*;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.*;

/**
 * @author xiechaofeng
 * @date 2018/7/10 14:41
 * @desc
 */
public class TestDict {
    public static void main(String[] args) {
        ParseBean parseBean=new ParseBean();
        try {
            File file = new File("C:\\Users\\xcf\\Desktop\\test.xml");
            SAXReader reader=new SAXReader();
            //读取xml文件到Document中
            Document doc=reader.read(file);
            //获取xml文件的根节点
            Element rootElement=doc.getRootElement();
            //定义一个Element用于遍历
            Element fooElement;

            List<Element> explanationList = rootElement.elements("n-g");
            for (Element node: explanationList) {
                ExplanationBean explanationBean=new ExplanationBean();
                Map<String,List<BaseBean>> singleBeanMap=new HashMap<>();
                List<Element> pareseNodeList = node.elements("sl-g");
                //找出来sb-g
                for (Element base : pareseNodeList) {
                    BaseBean baseBean=new BaseBean();
                    String sl = base.attribute("sl").getValue();
                    List<Element> cl = base.elements("cl");
                    for (Element clElement : cl) {
                        List<String> englishExplanation=new ArrayList<>();
                        List<String> chineseExplanation=new ArrayList<>();
                        Map<String,String> exampleExplanation=new HashMap<>();
                        String text = clElement.getTextTrim();
                        englishExplanation.add(text);


                    }


//                    singleBeanMap.put(sl,)
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}


