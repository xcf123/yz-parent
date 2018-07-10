package com.yuanzong.utils;

import com.yuanzong.beans.*;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

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
 */
public class HtmlUtils
{

    public static String getHtml(ParseBean word)
    {
        if (word == null)
        {
            return "";
        }
        StringBuilder p = new StringBuilder(1024);
        fillTitle(p, word);
        fillBody(p, word.explanationBeanList);
        return p.toString();
    }


    private static void fillSpeechDetailBean(StringBuilder p, List<SpeechDetailBean> speechDetailBeans)
    {
        if (speechDetailBeans != null)
        {
            for (int i = 0; i < speechDetailBeans.size(); i++)
            {
                if (i != 0)
                {
                    p.append("<b>┃</b>");
                }
                SpeechDetailBean speechDetailBean = speechDetailBeans.get(i);
                p.append("<b>");
                p.append(joinList(speechDetailBean.englishExplanation, ","));
                p.append("</b>");
                p.append("<span>");
                p.append(speechDetailBean.chineseExplanation);
                p.append("</span>");
                fillSection(p, speechDetailBean.sectionList);
            }
        }
    }

    private static void fillSection(StringBuilder p, List<Section> sectionList)
    {
        if (sectionList != null)
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
            sb.append(englishExplanation.get(0));
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
                p.append("<font face=\"serif\">•");
                p.append(baseBean.speech);
                p.append(".</font>");
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
                p.append("<font face=\"san-serif\"><b>");
                p.append(i + 1);
                p.append("</b>");
                p.append(explanationBean.englishPhrase);
                p.append(explanationBean.chinesePhrase);
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
            p.append("(also <font>");
            p.append(word.shaped);
            p.append("</font> )");
        }
        p.append("<small>");
        p.append(word.partOfSpeech);
        p.append("</small>");
        p.append("</p>");
    }

    public static String unespace(String s)
    {
        return s.replace("&lsquo;", "‘")
                .replace("&rsquo;", "’")
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
                .replace("&ecirc;", "Ê")
                .replace("&atilde;", "ã")
                .replace("&check;", " ")
                .replace("&egrave;", "è")
                .replace("&euml;", "ë")
                .replace("&acirc;", "Â")
                .replace("&aacute;", "á")
                .replace("&agrave;", "À")
                .replace("&sub2;", "-")
                .replace("&oacute;", "Ó")
                .replace("&iuml;", "ï")
                .replace("&ntilde;", "Ñ")
                .replace("&iacute;", "í")
                .replace("&Eacute;", "É")
                .replace("&uuml;", "Ü")
                .replace("&ugrave;", "ù")
                .replace("&ocirc;", "Ô");
    }
}
