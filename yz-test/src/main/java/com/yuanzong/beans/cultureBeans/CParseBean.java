package com.yuanzong.beans.cultureBeans;

import java.util.List;

/**
 * @author xiechaofeng
 * @date 2018/7/11 9:21
 * @desc
 */
public class CParseBean {
    /**
     * 单词前缀 如Margaret Beckett 中的Margaret
     */
    public String beforeName;

    /**
     * 单词英文名
     */
    public String englishName;
    /**
     * 标记 英文名是否需要斜体
     */
    public String flag;

    /**
     * ab  可能是简称
     */
    public String ab;

    /**
     * 别称解释
     */
    public String anotherNameExp;
    /**
     * 词性
     */
    public String partOfSpeech;
    /**
     * 单词音标
     */
    public List<String> phoneticSymbolList;

    /**
     * 美式音标
     */
    public List<String> amPhoneticSymbolList;

    /**
     * 美式还是英式
     */
    public String pronunciation;

    /**
     * 其他名字
     */
    public String anotherName;



    /**
     * 开头方括号部分 gr标签
     */
    public List<String> squareBracketsList;
    /**
     *
     */
    public List<CEnglishBaseBean> cEnglishBaseBeanList;

    /**
     * 单词中文名
     */
    public String chineseName;
    /**
     *
     */
    public List<CChineseBaseBean> cChineseBaseBeanList;

    public DrgBean drgBean;

    @Override
    public String toString() {
        return "CParseBean{" +
                "englishName='" + englishName + '\'' +
                ", partOfSpeech='" + partOfSpeech + '\'' +
                ", phoneticSymbolList=" + phoneticSymbolList +
                ", squareBracketsList=" + squareBracketsList +
                ", cEnglishBaseBeanList=" + cEnglishBaseBeanList +
                ", chineseName='" + chineseName + '\'' +
                ", cChineseBaseBeanList=" + cChineseBaseBeanList +
                '}';
    }
}
