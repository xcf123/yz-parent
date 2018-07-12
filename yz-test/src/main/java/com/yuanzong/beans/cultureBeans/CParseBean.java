package com.yuanzong.beans.cultureBeans;

import java.util.List;

/**
 * @author xiechaofeng
 * @date 2018/7/11 9:21
 * @desc
 */
public class CParseBean {
    /**
     * 单词英文名
     */
    public String englishName;
    /**
     * 词性
     */
    public String partOfSpeech;
    /**
     * 单词音标
     */
    public List<String> phoneticSymbolList;
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
