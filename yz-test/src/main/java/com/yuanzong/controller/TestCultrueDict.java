package com.yuanzong.controller;

import com.yuanzong.beans.BaseBean;
import com.yuanzong.beans.ExplanationBean;
import com.yuanzong.beans.ParseBean;
import com.yuanzong.beans.cultureBeans.*;
import com.yuanzong.utils.HtmlUtils;
import com.yuanzong.utils.HtmlUtils2;
import freemarker.template.utility.DateUtil;
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
            File file = new File("C:\\Users\\Administrator\\Desktop\\test2.xml");
            String s = FileUtils.readFileToString(file, "utf8");
            s = HtmlUtils.unespace(s);


            Pattern pattern2 = Pattern.compile("<dt><xr xt=\"ast\".*?><xh>(.*?)</xh>.*?</xr>.*?</dt>");
            Matcher m2 = pattern2.matcher(s);
            while(m2.find()){
                s=s.replaceFirst("<dt><xr xt=\"ast\".*?><xh>.*?</xh>.*?</xr>.*?</dt>","*"+m2.group(1));

            }

            Pattern pattern = Pattern.compile("<xr xt=\"ast\".*?><xh>(.*?)</xh>.*?</xr>");
            Matcher m = pattern.matcher(s);
            while(m.find()){
                s=s.replaceFirst("<xr xt=\"ast\".*?><xh>.*?</xh>.*?</xr>","*"+m.group(1));

            }

            s=s.replace("<!DOCTYPE batch SYSTEM \"ogbac.dtd\">","");
            s=s.trim();
            //读取xml文件到Document中
           //System.out.println(s);
            Document doc = DocumentHelper.parseText(s);
            //获取xml文件的根节点
            Element realRoot= doc.getRootElement();
            List<Element> entry = realRoot.elements("entry");
            int num=0;
            for (Element root : entry) {
                CParseBean cParseBean = new CParseBean();
                //得到英文单词
                Element h = root.element("h-g").element("h");
                Attribute h1 = h.attribute("h");
                if(h1!=null){
                    String htValue = h1.getValue();
                    if(htValue!=null && htValue.equals("ht")){
                        cParseBean.flag="1";
                    }
                }
                String englishName = h.getTextTrim();
                Element hr = h.element("hr");
                if(hr!=null){
                    String beforeName = hr.getTextTrim();
                    if (StringUtils.isNotBlank(beforeName)){
                        cParseBean.beforeName=beforeName;
                    }
                }
                Element g = root.element("g");
                if(g!=null){
                    String value = g.attribute("g").getValue();
                    if(value.equals("am")){
                        cParseBean.pronunciation="AmE";
                    }else if(value.equals("br")){
                        cParseBean.pronunciation="BrE";
                    }
                }

                //得到单词别称解释
                Element vsg = root.element("vs-g");
                if(vsg!=null){
                    Element q = vsg.element("q");
                    String anotherNameExp="";
                    if(q!=null){
                        anotherNameExp="("+q.getTextTrim();
                    }
                    List<Element> v = vsg.elements("v");
                    for(int i=0;i<v.size();i++){
                        anotherNameExp+="<b>"+v.get(i).getText()+"</b>"+"、";
                    }
                    if(anotherNameExp.endsWith("、")){
                        anotherNameExp=anotherNameExp.substring(0,anotherNameExp.length()-1);
                    }
                    Element ig = vsg.element("i-g");
                    if(ig!=null){
                        String i = ig.element("i").getText();
                        anotherNameExp+="<font>/"+i+"/</font>"+")";
                    }

                    Element r = vsg.element("r");
                    if(r!=null){
                        anotherNameExp="("+"<i>"+r.attribute("r").getValue()+"&nbsp;<i>";
                        Element vt = vsg.element("vt");
                        if(vt!=null){
                            Element vr = vt.element("vr");
                            if(vr!=null){
                                anotherNameExp+=vr.getText()+"&nbsp;";
                            }
                            anotherNameExp+="<b>"+vt.getText()+"</b>";
                        }
                        anotherNameExp+=")&nbsp;";
                    }

                    cParseBean.anotherNameExp=anotherNameExp;
                }

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
                    List<String> amPhoneticSymbolList=new ArrayList<>();
                    for (Element element : phoneticSymbol) {
                        String symbol = element.element("i").getTextTrim();
                        //音标转义
                        symbol=HtmlUtils.phoneticParse(symbol);
                        phoneticSymbolList.add(symbol);

                        Element y = element.element("y");
                        if(y!=null){
                            String amSymbol = y.getTextTrim();
                            amSymbol=HtmlUtils.phoneticParse(amSymbol);
                            amPhoneticSymbolList.add(amSymbol);
                        }
                    }
                    cParseBean.phoneticSymbolList=phoneticSymbolList;
                    cParseBean.amPhoneticSymbolList=amPhoneticSymbolList;
                }

                Element ab = root.element("ab");
                if(ab!=null){
                    cParseBean.ab=ab.getText();
                }


                Element ifsg = root.element("ifs-g");
                if(ifsg!=null){
                    Element ifg = ifsg.element("if-g");
                    Element il1 = ifg.element("il");
                    String anotherName="";
                    if(il1!=null){
                        String il = il1.attribute("il").getValue();
                        anotherName=il+"";
                    }
                    List<Element> anIf = ifg.elements("if");
                    for(int i=0;i<anIf.size();i++){
                        anotherName+=" "+anIf.get(i).getTextTrim()+" or";

                    }
                    anotherName=anotherName.substring(0,anotherName.length()-2);
                    cParseBean.anotherName=anotherName;
                }

                //中文公共部分
                Element ch = root.element("tr-g");
                if(ch!=null){
                    List<Node> nodes = ch.selectNodes("tr[@tr='h']");
                    //中文单词名
                    if(nodes!=null && nodes.size()!=0){
                        if(nodes.size()==1){
                            String name=nodes.get(0).getText();
                            cParseBean.chineseName=name;
                        }else{
                            String name=nodes.get(0).getText();
                            for(int i=1;i<nodes.size();i++){
                                name+="("+nodes.get(i).getText()+")";
                            }
                            cParseBean.chineseName=name;
                        }
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
                List<CChineseBaseBean> cChineseBaseBeanList=new ArrayList<>();
                if(ch!=null){
                    List<Element> cng = ch.elements("n-g");
                    if(cng!=null && cng.size()!=0){
                        for (Element element : cng) {
                            CChineseBaseBean cChineseBaseBean=new CChineseBaseBean();
                          //  Node singleNode = element.selectSingleNode("tr[@tr='h']");
                            List<Node> nodes = element.selectNodes("tr[@tr='h']");
                            //中文单词名
                            if(nodes!=null && nodes.size()!=0){
                                if(nodes.size()==1){
                                    String name=nodes.get(0).getText();
                                    cChineseBaseBean.name=name;
                                }else{
                                    String name=nodes.get(0).getText();
                                    cChineseBaseBean.name=name;
                                    for(int i=1;i<nodes.size();i++){
                                        name+="("+nodes.get(i).getText()+")";
                                    }
                                }
                            }

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
                }else{
                    CChineseBaseBean cChineseBaseBean=new CChineseBaseBean();
                    List<String> list = parseDetail(root);
                    cChineseBaseBean.chineseSeeDetailList=list;
                    cChineseBaseBean = chineseParse(root,cChineseBaseBean);
                    cChineseBaseBeanList.add(cChineseBaseBean);
                    cParseBean.cChineseBaseBeanList=cChineseBaseBeanList;
                }

                Element drg = root.element("dr-g");
                if(drg!=null){
                    DrgBean dr=new DrgBean();
                    //英文名
                    dr.englishName = drg.element("dr").getText();
                    //词性
                    Element drgp = drg.element("p");
                    if(drgp!=null){
                        String partOfSpeech = drgp.attribute("p").getValue();
                        dr.partOfSpeech=partOfSpeech;
                    }
                    Element d = drg.element("d");
                    if(d!=null){
                        CEnglishBaseBean cEnglishBaseBean=new CEnglishBaseBean();
                        List<String> list = parseDetail(drg);
                        cEnglishBaseBean.seeDetailList=list;
                        cEnglishBaseBean = parseEnglish(drg,cEnglishBaseBean);
                        dr.cEnglishBaseBean=cEnglishBaseBean;
                    }
                    Element element = drg.element("tr-g");
                    if(element!=null){
                        CChineseBaseBean cChineseBaseBean=new CChineseBaseBean();
                        List<String> list = parseDetail(element);
                        cChineseBaseBean.chineseSeeDetailList=list;
                        cChineseBaseBean = chineseParse(element,cChineseBaseBean);
                        dr.cChineseBaseBean=cChineseBaseBean;
                    }
                    cParseBean.drgBean=dr;
                }
                System.out.println("->"+num);
                num++;
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
            FileUtils.writeLines(new File("C:\\Users\\Administrator\\Desktop\\9.sql"), HtmlUtils2.cSql,false);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public  static CChineseBaseBean chineseParse(Element ch,CChineseBaseBean cChineseBaseBean){
        List<Element> tr = ch.elements("tr");
        List<Element> sn = ch.elements("sn");
        if(tr!=null && tr.size()!=0){
            String translation="";
            for (Element element : tr) {
                String value = element.attribute("tr").getValue();
                if(StringUtils.equals(value,"d") ||StringUtils.equals(value,"dr") ){
                    String s= element.getTextTrim();
                    translation+=s+"，";
                    //补充部分
                    List<Element> np = element.elements("np");
                    if(np!=null && np.size()!=0 ){
                        List<String> supplementList=new ArrayList<>();
                        for (Element npelement : np)
                        {
                            String text = npelement.getText();
                            supplementList.add(text);
                        }
                        cChineseBaseBean.supplementList=supplementList;
                    }
                }else if(StringUtils.equals(value,"snp")){
                    String s = element.getTextTrim();
                    translation+=s+"</br>";
                }
            }
            boolean b = translation.endsWith("，");
            if(b){
                cChineseBaseBean.translation=translation.substring(0,translation.length()-1);
            }else{
                cChineseBaseBean.translation=translation;
            }
            //例句翻译
            List<Element> x1 = ch.elements("x");
            if(x1!=null && x1.size()!=0){
                List<CSection> cSectionList=new ArrayList<>();
                List<Element> list = ch.selectNodes("tr[@tr='x']");
                for(int i=0;i<x1.size();i++){
                    CSection cSection=new CSection();
                    String cSectionEnglish = x1.get(i).getTextTrim();
                    String cSectionChinese="";
                    try
                    {
                        cSectionChinese= list.get(i).getTextTrim();
                    }
                    catch (IndexOutOfBoundsException e)
                    {
                        continue;
                    }
                    cSection.chinese=cSectionChinese;
                    cSection.english=cSectionEnglish;
                    cSectionList.add(cSection);
                }
                cChineseBaseBean.cSectionList=cSectionList;
            }
        }else if(sn!=null && sn.size()!=0){
            //大段翻译时调用
            Element sn1 = sn.get(1);
            Element trg = sn1.element("tr-g");
            if(trg!=null){
                List<Element> tr1 = trg.elements("tr");
                String translation="";
                for (Element element : tr1)
                {
                    String value = element.attribute("tr").getValue();
                    if(StringUtils.equals(value,"h")){
                        String name = element.getTextTrim();
                        cChineseBaseBean.name=name;
                        //补充部分
                        List<Element> np = ch.elements("np");
                        if(np!=null && np.size()!=0 ){
                            List<String> supplementList=new ArrayList<>();
                            for (Element npelement : np)
                            {
                                String text = npelement.getText();
                                supplementList.add(text);
                            }
                            cChineseBaseBean.supplementList=supplementList;
                        }
                    }else if(StringUtils.equals(value,"snp")){
                         String str= element.getTextTrim();
                            translation+=str+"</br>&nbsp;&nbsp;&nbsp;&nbsp;";
                    }
                }
                cChineseBaseBean.translation=translation;

            }
        }

        return cChineseBaseBean;
    }

    public  static CEnglishBaseBean parseEnglish(Element eng,CEnglishBaseBean cEnglishBaseBean){


        //语法信息
        List<Element> gr = eng.elements("gr");
        if(gr!=null && gr.size()!=0){
            List<String> squareBracketsList=new ArrayList<>();
            for (Element element : gr) {
                Attribute q = element.attribute("q");
                if(q!=null){
                    String squareBrackets2 = q.getValue();
                    squareBracketsList.add(squareBrackets2);
                }
                String squareBrackets = element.attribute("gr").getValue();
                squareBracketsList.add(squareBrackets);
            }
            cEnglishBaseBean.squareBracketsList=squareBracketsList;
        }

        Element r = eng.element("r");
        if(r!=null){
            Attribute q1 = r.attribute("q");
            String q="";
            String r1="";
            if(q1!=null){
                q= r.attribute("q").getValue();
            }
            if(r1!=null){
                r1= r.attribute("r").getValue();
            }
            cEnglishBaseBean.annotation=q+" "+r1;
        }

        //人名有全名时
        Element eh = eng.element("eh");
        String entireName="";
        if(eh!=null){
            entireName=eh.getText();
        }
        Element dab = eng.element("dab");
        if(dab!=null){
            entireName+=" "+dab.getText();
        }
        cEnglishBaseBean.entireName=entireName;

        //英文部分
        List<Element> des = eng.elements("d");
        Element sn = eng.element("sn");
        if(des!=null && des.size()!=0){
            Element d = des.get(0);
            Element ds = d.element("ds");
            if(ds!=null){
                String brackets = ds.getTextTrim();
                cEnglishBaseBean.brackets=brackets;
            }
            String english = d.getTextTrim();

            /*List<Element> dt = d.elements("dt");
            List<Element> dab1 = d.elements("dab");
            if(dt!=null && dt.size()!=0){
                if(dt.get(0).getText().equals("The Magnificent Seven")){
                    english=english.substring(0,english.length()-7);
                }
                for (int i=0;i<dt.size();i++)
                {
                    if(i>dab1.size() || dab1.size()==0){
                        english+="<i>"+dt.get(i).getText()+"</i>";
                    }else {
                        english+="<i>"+dt.get(i).getText()+"</i>"+"("+dab1.get(i).getText()+")、";
                    }
                }
            }*/
            if(des.size()!=1){
                for(int i=1;i<des.size();i++){
                    english+="<br>"+des.get(i).getText();
                }
            }
            cEnglishBaseBean.english=english;

            Element qt = d.element("qt");
          
            if(qt!=null){
                List<String> lyricsList=new ArrayList<>();
                List<Element> ql = qt.elements("ql");
                for (Element element : ql)
                {
                    String text = element.getText();
                    lyricsList.add(text);
                }
                cEnglishBaseBean.lyricsList=lyricsList;
            }

        }else if(sn!=null){
            List<Element> snp = sn.elements("snp");
            if(snp!=null && snp.size()!=0){
                String english="";
                for (Element element : snp) {
                    english+=element.getTextTrim()+"</br>&nbsp;&nbsp;&nbsp;&nbsp;";
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

        List<Node> nodes2 = element.selectNodes("xr[@xt='ntat']");
        if(nodes2!=null && nodes2.size()!=0){
            for (Node node : nodes2) {
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
