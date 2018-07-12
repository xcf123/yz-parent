package com.yuanzong.controller;

import com.yuanzong.beans.BaseBean;
import com.yuanzong.beans.ExplanationBean;
import com.yuanzong.beans.ParseBean;
import com.yuanzong.beans.cultureBeans.CChineseBaseBean;
import com.yuanzong.beans.cultureBeans.CEnglishBaseBean;
import com.yuanzong.beans.cultureBeans.CParseBean;
import com.yuanzong.beans.cultureBeans.CSection;
import com.yuanzong.utils.HtmlUtils;
import com.yuanzong.utils.HtmlUtils2;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.*;
import sun.util.resources.cldr.ps.CurrencyNames_ps;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xiechaofeng
 * @date 2018/7/11 9:20
 * @desc
 */
public class TestCultrueDict {
    public static void main(String[] args) {
        try {
            File file = new File("C:\\Users\\xcf\\Desktop\\test(2).xml");
            String s = FileUtils.readFileToString(file, "utf8");
            s = HtmlUtils.unespace(s);

            Pattern pattern = Pattern.compile("<xr xt=\"ast\".*?><xh>(.*?)</xh>.*?</xr>");
            Matcher m = pattern.matcher(s);
            while(m.find()){
                s=s.replaceFirst("<xr xt=\"ast\".*?><xh>.*?</xh>.*?</xr>","*"+m.group(1));
               // System.out.println(s);
            }
            s=s.replace("<!DOCTYPE batch SYSTEM \"ogbac.dtd\">","");
            s=s.trim();
            //读取xml文件到Document中
           //System.out.println(s);
            Document doc = DocumentHelper.parseText(s);
            //获取xml文件的根节点
            Element realRoot= doc.getRootElement();
            List<Element> entry = realRoot.elements("entry");
            for (Element root : entry) {
                CParseBean cParseBean = new CParseBean();
                //得到英文单词
                String englishName = root.element("h-g").element("h").getTextTrim();
                cParseBean.englishName=englishName;
                //得到词性
                Element p = root.element("p");
                if(p!=null){
                    String partOfSpeech = p.attribute("p").getValue();
                    cParseBean.partOfSpeech=partOfSpeech;
                }
                //得到音标
                List<Element> phoneticSymbol = root.elements("i-g");
                if(phoneticSymbol!=null && phoneticSymbol.size()!=0){
                    List<String> phoneticSymbolList=new ArrayList<>();
                    for (Element element : phoneticSymbol) {
                        String symbol = element.element("i").getTextTrim();
                        phoneticSymbolList.add(symbol);
                    }
                    cParseBean.phoneticSymbolList=phoneticSymbolList;
                }

                //方括号部分
                List<Element> gr = root.elements("gr");
                if(gr!=null && gr.size()!=0){
                    List<String> squareBracketsList=new ArrayList<>();
                    for (Element element : gr) {
                        String squareBrackets = element.attribute("gr").getValue();
                        squareBracketsList.add(squareBrackets);
                    }
                    cParseBean.squareBracketsList= squareBracketsList;
                }


                //中文公共部分
                Element ch = root.element("tr-g");
                if(ch!=null){
                    Node singleNode = ch.selectSingleNode("tr[@tr='h']");
                    //中文单词名
                    if(singleNode!=null){
                        String chineseName=singleNode.getText();
                        cParseBean.chineseName=chineseName;
                    }
                }

                List<Element> eng = root.elements("n-g");
                List<CEnglishBaseBean> cEnglishBaseBeanList=new ArrayList<>();
                //英语列表
                if(eng!=null && eng.size()!=0){

                    for (Element element : eng) {
                        CEnglishBaseBean cEnglishBaseBean=new CEnglishBaseBean();
                        List<String> list = parseDetail(element);
                        //详见
                        cEnglishBaseBean.seeDetailList=list;
                        cEnglishBaseBean = parseEnglish(element,cEnglishBaseBean);
                        cEnglishBaseBeanList.add(cEnglishBaseBean);
                    }
                    cParseBean.cEnglishBaseBeanList=cEnglishBaseBeanList;
                }else{
                    CEnglishBaseBean cEnglishBaseBean=new CEnglishBaseBean();
                    List<String> list = parseDetail(root);
                    cEnglishBaseBean.seeDetailList=list;
                    cEnglishBaseBean = parseEnglish(root,cEnglishBaseBean);
                    cEnglishBaseBeanList.add(cEnglishBaseBean);
                    cParseBean.cEnglishBaseBeanList=cEnglishBaseBeanList;
                }
                //中文列表
                if(ch!=null){
                    List<Element> cng = ch.elements("n-g");
                    List<CChineseBaseBean> cChineseBaseBeanList=new ArrayList<>();
                    if(cng!=null && cng.size()!=0){
                        for (Element element : cng) {
                            CChineseBaseBean cChineseBaseBean=new CChineseBaseBean();
                            List<String> list = parseDetail(element);
                            cChineseBaseBean.chineseSeeDetailList=list;
                            cChineseBaseBean = chineseParse(element,cChineseBaseBean);
                            cChineseBaseBeanList.add(cChineseBaseBean);
                        }
                        cParseBean.cChineseBaseBeanList=cChineseBaseBeanList;
                    }else{
                        CChineseBaseBean cChineseBaseBean=new CChineseBaseBean();
                        List<String> list = parseDetail(ch);
                        cChineseBaseBean.chineseSeeDetailList=list;
                        cChineseBaseBean = chineseParse(ch,cChineseBaseBean);
                        cChineseBaseBeanList.add(cChineseBaseBean);
                        cParseBean.cChineseBaseBeanList=cChineseBaseBeanList;
                    }
                }


               // System.out.println(HtmlUtils2.getHtml(cParseBean));
                HtmlUtils2.recordInsertSql(cParseBean);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        try
        {
            FileUtils.writeLines(new File("C:\\Users\\xcf\\Desktop\\9.sql"), HtmlUtils2.cSql,false);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public  static CChineseBaseBean chineseParse(Element ch,CChineseBaseBean cChineseBaseBean){
        List<Element> tr = ch.elements("tr");
        for (Element element : tr) {
            String value = element.attribute("tr").getValue();
             if(StringUtils.equals(value,"d")){
                String translation = element.getTextTrim();
                cChineseBaseBean.translation=translation;
            }else if(StringUtils.equals(value,"snp")){
                 String translation = element.getTextTrim();
                 cChineseBaseBean.translation=translation+"</br>";
             }
        }
        //例句翻译
        List<Element> x1 = ch.elements("x");
        if(x1!=null && x1.size()!=0){
            List<CSection> cSectionList=new ArrayList<>();
            List<Element> list = ch.selectNodes("tr[@tr='x']");
            for(int i=0;i<x1.size();i++){
                CSection cSection=new CSection();
                String cSectionEnglish = x1.get(i).getTextTrim();
                String cSectionChinese = list.get(i).getTextTrim();
                cSection.chinese=cSectionChinese;
                cSection.english=cSectionEnglish;
                cSectionList.add(cSection);
            }
            cChineseBaseBean.cSectionList=cSectionList;
        }
        return cChineseBaseBean;
    }

    public  static CEnglishBaseBean parseEnglish(Element eng,CEnglishBaseBean cEnglishBaseBean){
        //英文部分
        Element d = eng.element("d");
        Element sn = eng.element("sn");
        if(d!=null){
            Element ds = d.element("ds");
            if(ds!=null){
                String brackets = ds.getTextTrim();
                cEnglishBaseBean.brackets=brackets;
            }
            String english = d.getTextTrim();
            cEnglishBaseBean.english=english;
        }else if(sn!=null){
            List<Element> snp = sn.elements("snp");
            if(snp!=null && snp.size()!=0){
                String english="";
                for (Element element : snp) {
                    english+=element.getTextTrim()+"</br>";
                }
                cEnglishBaseBean.english=english;
            }
        }
        //着重部分，缩写全称 ff标签
        Element ff = eng.element("ff");
        if(ff!=null){
            String abbreviation = ff.getTextTrim();
            cEnglishBaseBean.abbreviation=abbreviation;
        }
        //例句
        List<Element> x = eng.elements("x");
        if(x!=null && x.size()!=0){
            List<String> exampleSentenceList =new ArrayList<>();
            for (Element element : x) {
                String exampleSentence = element.getTextTrim();
                exampleSentenceList.add(exampleSentence);
            }
            cEnglishBaseBean.exampleSentenceList=exampleSentenceList;
        }
        return cEnglishBaseBean;
    }

    public static  List<String>  parseDetail(Element element){
        List<Node> nodes = element.selectNodes("xr[@xt='arrow']");
        List<String> seeDetailList=new ArrayList<>();
        if(nodes!=null && nodes.size()!=0){
            for (Node node : nodes) {
                Node selectSingleNode = node.selectSingleNode("xh");
                Node selectSingleNode1 = node.selectSingleNode("xhi");
                if(selectSingleNode!=null){
                    String detail =selectSingleNode.getText();
                    seeDetailList.add(detail);
                }else if(selectSingleNode1!=null){
                    String detail =selectSingleNode1.getText();
                    seeDetailList.add(detail);
                }
            }
        }
        return seeDetailList;
    }

}
