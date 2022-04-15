package com.yuanzong.controller;

import com.yuanzong.beans.*;
import com.yuanzong.utils.HtmlUtils;
import org.apache.commons.io.FileUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import javax.swing.*;
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
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            JFileChooser fc = new JFileChooser("C:");
            fc.setDialogTitle("选择牛津词典的xml文件");
            //是否可多选
            fc.setMultiSelectionEnabled(false);
            //选择模式，可选择文件和文件夹
            //fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            //		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            //设置是否显示隐藏文件
            fc.setAcceptAllFileFilterUsed(false);


            int returnValue = fc.showOpenDialog(null);
            File file = null;
            if (returnValue == JFileChooser.APPROVE_OPTION)
            {
                file = fc.getSelectedFile();
            }else{
                System.err.println("没有选择文件，退出");
                return;
            }

            List<String> utf8 = FileUtils.readLines(file, "utf8");
            int i = 0;
            for (String s : utf8)
            {
                if (!s.startsWith("<entry"))
                {
                    continue;
                }
                s = HtmlUtils.unespace(s);
                ParseBean parseBean = new ParseBean();
                //读取xml文件到Document中
                Document doc = DocumentHelper.parseText(s);
                //获取xml文件的根节点
                Element rootElement = doc.getRootElement();
                List<ExplanationBean> explanationBeanList = new ArrayList<>();
                System.out.println("->"+i);
                i++;
                //读取单词名和词性
                String name = rootElement.element("h").getTextTrim();
                List<Element> pes = rootElement.elements("p");
                parseBean.name = name;
                if (pes != null && pes.size()!=0)
                {
                    List<String> partOfSpeechList=new ArrayList<>();
                    for (Element p : pes)
                    {
                        String partOfSpeech = p.attributeValue("p");
                        partOfSpeechList.add(partOfSpeech);
                    }
                    parseBean.partOfSpeechList=partOfSpeechList;
                }

                //单词用法
                Element d1 = rootElement.element("d");
                if(d1!=null){
                    String text = d1.getText();
                    Element chn = d1.element("chn");
                    if(chn!=null){
                        text+=" "+chn.getText();
                    }
                    parseBean.way=text;
                }

                List<Element> explanationList = rootElement.elements("n-g");
                List<Element> elements = rootElement.elements("sl-g");
                List<Element> pvsg = rootElement.elements("pvs-g");
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
                        Element xr = node.element("xr");
                        if(xr !=null){
                            String xt = xr.attribute("xt").getValue();
                            String note="";
                            if(xt.equals("page")){
                                note="⇨Special page at ";
                            }else if(xt.equals("see")){
                                note="⇨see ";
                            }else if(xt.equals("seealso")){
                                note="⇨see also ";
                            }
                            else {
                                note="⇨NOTE at ";
                            }
                            explanationBean.explainNote=note+xr.element("xh").getTextTrim().toUpperCase();
                        }

                        List<Element> pareseNodeList = node.elements("sl-g");
                        //找出来sl-g
                        fillBaseBean(pareseNodeList,explanationBean);
                        explanationBeanList.add(explanationBean);
                    }
                }

                //其他 pvs
                List<PvsBean> pvsBeanList=new ArrayList<>();
                if(pvsg!=null && pvsg.size()!=0){

                    for (Element element : pvsg)
                    {
                        PvsBean pvsBean=new PvsBean();
                        ExplanationBean explanationBean = new ExplanationBean();
                        String pv = element.element("pv").getText();
                        pvsBean.psvExplanation=pv;

                        List<Element> slg1 = element.elements("sl-g");
                        //找出来sl-g
                        fillBaseBean(slg1,explanationBean);
                        pvsBean.explanationBean=explanationBean;
                        pvsBeanList.add(pvsBean);
                    }
                }
                parseBean.pvsBeanList=pvsBeanList;
                parseBean.explanationBeanList = explanationBeanList;


                Element xr = rootElement.element("xr");
                if(xr !=null){
                    String xt = xr.attribute("xt").getValue();
                    String note="";
                    if(xt.equals("page")){
                        note="⇨Special page at ";
                    }else if(xt.equals("see")){
                         note="⇨see ";
                    }else if(xt.equals("seealso")){
                        note="⇨see also ";
                    }
                    else {
                         note="⇨NOTE at ";
                    }
                    parseBean.note =note+ xr.element("xh").getTextTrim().toUpperCase();
                }
                //读取异形词
                Element vsG = rootElement.element("vs-g");
                if(vsG !=null){
                    Element v1 = vsG.element("v");
                    Element vs = vsG.element("vs");
                    if(v1!=null){
                        parseBean.shaped = v1.getTextTrim();
                    }else if(vs!=null){
                        parseBean.shaped = vs.getTextTrim();
                    }
                }
                //英式发音
                Element he = rootElement.element("he");
                if(he!=null){
                    parseBean.englishVoice=he.getText();
                }

                //其他信息
                Element r = rootElement.element("r");
                if(r!=null){
                    parseBean.other=r.getTextTrim();
                }
               // System.out.println(HtmlUtils.getHtml(parseBean));
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
                    Element note = clElement.element("note");
                    if(note!=null){
                        speechDetailBean.note=note.getTextTrim();
                    }
                    Element chn = clElement.element("chn");
                    if (chn != null)
                    {
                        chineseExplanation = chn.getTextTrim();
                    }
                }
                //其他用途
                Element r = speechDetail.element("r");
                if(r!=null){
                    speechDetailBean.otherUse=r.getTextTrim();
                }
                //针对单词的中英文标注
                Element note = speechDetail.element("note");
                if(note!=null){
                    Attribute nt = note.attribute("nt");
                    if(nt!=null){
                        String value = nt.getValue();
                        if(value.equals("afterxr")){
                            String noteFor="";
                            Element xr = speechDetail.element("xr");
                            String xt = xr.attribute("xt").getValue();
                            if(xt.equals("page")){
                                noteFor="⇨Special page at ";
                            }else if(xt.equals("see")){
                                noteFor="⇨see ";
                            }else if(xt.equals("seealso")){
                                noteFor="⇨see also ";
                            }
                            else {
                                noteFor="⇨NOTE at ";
                            }
                            noteFor+=xr.getText();
                            speechDetailBean.finalNote=noteFor+"("+note.getText()+")";
                        }else{
                            speechDetailBean.englishNote=note.getText();
                        }
                        Element chn = note.element("chn");
                        if(chn!=null){
                            speechDetailBean.chineseNote=chn.getText();
                        }
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


