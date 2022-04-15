package com.yuanzong.utils;

import com.yuanzong.beans.*;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于拼接解释
 * <p>
 * <font><big>envy</big></font>(also <font>invy</font> )
 * <small>verb</small>
 * </p>
 * <font face="san-serif"><b>1</b> strong feeling of dislike 羡慕</font>
 * <p>
 * &nbsp;&nbsp;
 * <font face="serif">•ADV.</font>
 *
 * <b>greatly,really</b>
 * <span>极为羡慕；十分羡慕</span>
 *
 * <b>|</b>
 *
 * <b>secretly</b>
 * <span>暗暗羡慕</span>
 * </p>
 * <p>
 * &nbsp;&nbsp;
 * <font face="serif">•PREP.</font>
 * <b>for</b>
 * <span>羡慕···</span>
 * <span>：</span>
 * <font face="serif"><i >I secretly envied her for her good looks.</i></font>
 * <span>我暗暗羡慕她姣好的面容。</span>
 * ◇
 * <font face="serif"><i >I secretly envied her for her good looks.</i></font>
 * <span>我暗暗羡慕她姣好的面容。</span>
 * </p>
 *
 */
public class HtmlUtils
{
    public static  List<String> sql = new ArrayList<>(1024);
    public static String getHtml(ParseBean word)
    {
        if (word == null)
        {
            return "";
        }
        StringBuilder p = new StringBuilder(1024);
        fillTitle(p, word);
        fillBody(p, word.explanationBeanList);
        fillPvs(p,word.pvsBeanList);
        fillNote(p, word.note);
        return p.toString();
    }

    private static void fillPvs(StringBuilder p, List<PvsBean> pvsBeanList)
    {
        if (pvsBeanList != null)
        {
            for (int i = 0; i < pvsBeanList.size(); i++)
            {
                PvsBean pvsBean = pvsBeanList.get(i);
                ExplanationBean explanationBean = pvsBean.explanationBean;
                p.append("<font face=\"san-serif\">" );
                if(!StringUtils.isEmpty(pvsBean.psvExplanation)){
                    p.append("<b>");
                    p.append("PHR V&nbsp;&nbsp;"+pvsBean.psvExplanation);
                    p.append("</b>&nbsp;&nbsp;");
                }

                if(explanationBean.englishPhrase!=null){
                    p.append(explanationBean.englishPhrase);
                    p.append("&nbsp;&nbsp;");
                }
                if(explanationBean.chinesePhrase!=null){
                    p.append(explanationBean.chinesePhrase);
                    p.append("&nbsp;&nbsp;");
                }
                if(explanationBean.explainNote!=null){
                    p.append(explanationBean.explainNote);
                }
                p.append("</font>");
                fillBaseBean(p, explanationBean.baseBeanList);
            }
        }
    }

    private static void fillNote(StringBuilder p, String note)
    {
        if(note == null){
            return;
        }
        if(!note.startsWith("⇨see also")){
            p.append("<p> ");
            p.append(note);
            p.append("</p>");
        }
    }

    public static String recordInsertSql(ParseBean word)
    {
        if (word == null)
        {
            return "";
        }
        String name = word.name;
        String html = getHtml(word);
//        System.out.println(html);
        String sql =  "insert into dict_oxford_ec (`key`,`value`)values ('" + name +"','" +html.replace("'","''")+ "');";
        HtmlUtils.sql.add(sql);
        return sql;
    }


    private static void fillSpeechDetailBean(StringBuilder p, List<SpeechDetailBean> speechDetailBeans)
    {
        if (speechDetailBeans != null)
        {
            for (int i = 0; i < speechDetailBeans.size(); i++)
            {
                if (i != 0)
                {
                    p.append("<b>┃&nbsp;&nbsp;</b>");
                }
                SpeechDetailBean speechDetailBean = speechDetailBeans.get(i);
                p.append("<b>");
                p.append(joinList(speechDetailBean.englishExplanation, ","));
                p.append("</b>&nbsp;&nbsp;");
                if(!StringUtils.isEmpty(speechDetailBean.note)){
                    p.append("("+speechDetailBean.note+")&nbsp;");
                }
                if(!StringUtils.isEmpty(speechDetailBean.englishNote) || !StringUtils.isEmpty(speechDetailBean.chineseNote)){
                    p.append("(");
                    if(!StringUtils.isEmpty(speechDetailBean.englishNote)){
                        p.append(speechDetailBean.englishNote+"&nbsp;");
                    }
                    if(!StringUtils.isEmpty(speechDetailBean.chineseNote)){
                        p.append(speechDetailBean.chineseNote+"&nbsp;");
                    }
                    p.append(")");
                }
                if(!StringUtils.isEmpty(speechDetailBean.otherUse)){
                    p.append("<i>");
                    p.append("("+speechDetailBean.otherUse+")");
                    p.append("</i>&nbsp;&nbsp;");
                }
                p.append("<span>");
                p.append(speechDetailBean.chineseExplanation);
                p.append("</span>");
                fillSection(p, speechDetailBean.sectionList);
                if(!StringUtils.isEmpty(speechDetailBean.finalNote)){
                    p.append("&nbsp;&nbsp;"+speechDetailBean.finalNote);
                }
            }
        }
    }

    private static void fillSection(StringBuilder p, List<Section> sectionList)
    {
        if (sectionList != null && !sectionList.isEmpty())
        {
            p.append("<span>：</span>");
            for (int i = 0; i < sectionList.size(); i++)
            {
                if (i != 0)
                {
                    p.append("◇");
                }
                p.append("<font face=\"serif\"><i >");
                p.append(sectionList.get(i).english);
                p.append("</i></font>");
                p.append("<span>");
                p.append(sectionList.get(i).chinese);
                p.append("</span>");
            }
        }
    }

    private static StringBuilder joinList(List<String> englishExplanation, String s)
    {

        StringBuilder sb = new StringBuilder(64);
        if (CollectionUtils.isEmpty(englishExplanation))
        {
            return sb;
        }
        for (int i = 0; i < englishExplanation.size(); i++)
        {
            if (i != 0)
            {
                sb.append(s);
            }
            sb.append(englishExplanation.get(i));
        }
        return sb;
    }


    private static void fillBaseBean(StringBuilder p, List<BaseBean> baseBeanList)
    {
        if (baseBeanList != null)
        {
            for (int i = 0; i < baseBeanList.size(); i++)
            {
                BaseBean baseBean = baseBeanList.get(i);
                p.append("<p>&nbsp;&nbsp;");
                if(baseBean.speech !=null){
                    p.append("<font face=\"serif\">•");
                    String speech=baseBean.speech;
                    p.append(speech.toUpperCase());
                    p.append(".</font>&nbsp;&nbsp;");
                }
                fillSpeechDetailBean(p, baseBean.speechDetailBeans);
                p.append("</p>");
            }
        }
    }


    private static void fillBody(StringBuilder p, List<ExplanationBean> explanationBeanList)
    {
        if (explanationBeanList != null)
        {
            for (int i = 0; i < explanationBeanList.size(); i++)
            {
                ExplanationBean explanationBean = explanationBeanList.get(i);
                p.append("<font face=\"san-serif\">" );
                if(explanationBeanList.size() > 1){
                    p.append("<b>");
                    p.append(i + 1);
                    p.append("</b>&nbsp;&nbsp;");
                }

                if(explanationBean.englishPhrase!=null){
                    p.append(explanationBean.englishPhrase);
                    p.append("&nbsp;&nbsp;");
                }
                if(explanationBean.chinesePhrase!=null){
                    p.append(explanationBean.chinesePhrase);
                    p.append("&nbsp;&nbsp;");
                }
                if(explanationBean.explainNote!=null){
                    p.append(explanationBean.explainNote);
                }
                p.append("</font>");
                fillBaseBean(p, explanationBean.baseBeanList);
            }
        }
    }


    private static void fillTitle(StringBuilder p, ParseBean word)
    {
        p.append("<p>");
        p.append("<font><big>");
        p.append(word.name);
        p.append("</big></font>");
        if (!StringUtils.isEmpty(word.shaped))
        {
            p.append("&nbsp;("+"<i>also&nbsp;</i>"+ "<font>");
            p.append(word.shaped);
            p.append("</font> )");
        }
        if(!StringUtils.isEmpty(word.englishVoice)){
            p.append("&nbsp;("+"<i>BrE<i>&nbsp;"+"also <font>");
            p.append(word.englishVoice);
            p.append("</font> )");
        }
        p.append("<small>&nbsp;&nbsp;");
        p.append("<i>");
        if(word.partOfSpeechList!=null && word.partOfSpeechList.size()!=0){
            for (String s : word.partOfSpeechList)
            {
                if(s.equals("v")){
                    s="verb";
                }else if(s.equals("n")){
                    s="noun";
                }
                p.append(s+"&nbsp;&nbsp;");
            }
        }
        if(!StringUtils.isEmpty(word.way)){
            p.append("&nbsp;&nbsp;");
            p.append("("+word.way+")");
        }

        if(!StringUtils.isEmpty(word.note)){
            if(word.note.startsWith("⇨see also")){
                p.append("&nbsp;&nbsp;"+word.note);
            }
        }

        if(!StringUtils.isEmpty(word.other)){
            p.append("<i>");
            p.append("("+word.other+")");
            p.append("</i>");
        }
        p.append("</i>");
        p.append("</small>");
        p.append("</p>");
    }

    public static String unespace(String s)
    {
        return s.replace("&lsquo;", "‘")
                .replace("&rsquo;", "’")
                .replace("&eth;", "ð")
                .replace("&hellip;", "…")
                .replace("&cw;", "·")
                .replace("&mdash;", "—")
                .replace("&ndash;", "—")
                .replace("&dash2;", "—")
                .replace("&thinsp;", " ")
                .replace("&dollar;", "$")
                .replace("&pound;", "£")
                .replace("&percnt;", "%")
                .replace("&deg;", "°")
                .replace("&eacute;", "É")
                .replace("&trade;", "™")
                .replace("&euro;", "€")
                .replace("&ecirc;", "ê")
                .replace("&atilde;", "ã")
                .replace("&check;", " ")
                .replace("&egrave;", "è")
                .replace("&euml;", "ë")
                .replace("&acirc;", "Â")
                .replace("&aacute;", "á")
                .replace("&agrave;", "À")
                .replace("&sub2;", "₂")
                .replace("&oacute;", "ó")
                .replace("&iuml;", "ï")
                .replace("&ntilde;", "ñ")
                .replace("&iacute;", "í")
                .replace("&Eacute;", "É")
                .replace("&uuml;", "ü")
                .replace("&ugrave;", "ù")
                .replace("&ocirc;", "Ô")
                .replace("&ast;","*")
                .replace("<gl>","(=")
                .replace("</gl>",")")
                .replace("&s;","ˌ")
                .replace("&p;","ˈ")
                .replace("&ldquo;","“")
                .replace("&rdquo;","”")
                .replace("<da>","")
                .replace("</da>","")
                .replace("&ccedil;","ç")
                .replace("&frac12;","½")
                .replace("&ouml;","Ö")
                .replace("&uacute;","ú")
                .replace("&sup2;","2")
                .replace("&rcaron;","ř")
                .replace("&aacute;","á")
                .replace("&auml;","ä")
                .replace("&times;","×")
                .replace("&minus;","-")
                .replace("&thorn;","þ")
                .replace("&acirc;","â")
                .replace("&frac14;","¼")
                .replace("&aelig;","æ")
                .replace("&ieth;","ð")
                .replace("&AElig;","Æ")
                .replace("&alpha;","α")
                .replace("&beta;","β")
                .replace("&gamma;","γ")

                .replace("<db>","|++")
                .replace("</db>","++|")
                .replace("<di>","|--")
                .replace("</di>","--|")
                .replace("<dt>","|--")
                .replace("</dt>","--|")
                .replace("<dab>"," (")
                .replace("</dab>",") ")
                .replace("<ei>","")
                .replace("</ei>","")
                .replace("<da>","")
                .replace("</da>","")
                .replace("<a>","|++")
                .replace("</a>","++|")
                .replace("<xhm pr=\"y\">","||+")
                .replace("<xhm>","||+")
                .replace("</xhm>","+||")
                ;
    }

    public static String phoneticParse(String symbol)
    {
        return symbol.replace("3","ə")
                     .replace("\"","ˈ")
                     .replace("%","ˈ")
                     .replace("&","æ")
                     .replace(":","ː")
                     .replace("@","ə")
                     .replace("D","ð")
                     .replace("3","ə")
                     .replace("E","ə")
                     .replace("I","ɪ")
                     .replace("N","ŋ")
                     .replace("O","ɔ")
                     .replace("Q","ɒ")
                     .replace("S","ʃ")
                     .replace("T","θ")
                     .replace("U","ʊ")
                     .replace("V","ʌ")
                     .replace("Z","ʒ");

    }
}
