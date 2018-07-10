package com.yuanzong.controller;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import sun.security.ec.ECDSASignature;

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


                    singleBeanMap.put(sl,)
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}


class ParseBean{
    /**
     * 异型词
     */
    String Shaped;
    /**
     * 单词词性
     */
    String  partOfSpeech;

    List<ExplanationBean> explanationBeanList;

    public String getShaped() {
        return Shaped;
    }

    public void setShaped(String shaped) {
        Shaped = shaped;
    }

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public void setPartOfSpeech(String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }

    public List<ExplanationBean> getExplanationBeanList() {
        return explanationBeanList;
    }

    public void setExplanationBeanList(List<ExplanationBean> explanationBeanList) {
        this.explanationBeanList = explanationBeanList;
    }
}

class ExplanationBean{
    /**
     * 简单的英语短语解释
     */
    String englishPhrase;
    /**
     * 英文短语翻译
     */
    String chinesePhrase;
    /**
     * map<搭配的词性,bean>
     */
    Map<String,BaseBean> baseBeanList;

    public String getEnglishPhrase() {
        return englishPhrase;
    }

    public void setEnglishPhrase(String englishPhrase) {
        this.englishPhrase = englishPhrase;
    }

    public String getChinesePhrase() {
        return chinesePhrase;
    }

    public void setChinesePhrase(String chinesePhrase) {
        this.chinesePhrase = chinesePhrase;
    }

    public Map<String, BaseBean> getBaseBeanList() {
        return baseBeanList;
    }

    public void setBaseBeanList(Map<String, BaseBean> baseBeanList) {
        this.baseBeanList = baseBeanList;
    }
}

class BaseBean{
    /**
     * 英文单词解释
     */
    List<String> englishExplanation;
    /**
     * 中文单词解释
     */
    List<String> chineseExplanation;
    /**
     * 例句map<英语例句，中文翻译>
     */
    Map<String,String> exampleExplanation;

    public List<String> getEnglishExplanation() {
        return englishExplanation;
    }

    public void setEnglishExplanation(List<String> englishExplanation) {
        this.englishExplanation = englishExplanation;
    }

    public List<String> getChineseExplanation() {
        return chineseExplanation;
    }

    public void setChineseExplanation(List<String> chineseExplanation) {
        this.chineseExplanation = chineseExplanation;
    }

    public Map<String, String> getExampleExplanation() {
        return exampleExplanation;
    }

    public void setExampleExplanation(Map<String, String> exampleExplanation) {
        this.exampleExplanation = exampleExplanation;
    }
}
