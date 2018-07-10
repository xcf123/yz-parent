package com.yuanzong.beans;

import java.util.List;

public class SpeechDetailBean{
    /**
     * 英文单词解释
     */
    public List<String> englishExplanation;
    /**
     * 中文单词解释
     */
    public String chineseExplanation;
    /**
     * 例句map<英语例句，中文翻译>
     */
    public List<Section> sectionList;

}
