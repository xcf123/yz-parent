package com.yuanzong.beans;

import java.util.List;

public class SpeechDetailBean{
    /**
     * 英文单词解释
     */
    public List<String> englishExplanation;
    /**
     * 常用(其他用途)
     */
    public String otherUse;
    /**
     * 针对英文单词的标注
     */
    public String note;

    /**
     * 针对解释的（英文标注）
     */
    public String englishNote;

    /**
     * 针对解释的（中文标注）
     */
    public String chineseNote;
    /**
     * 中文单词解释
     */
    public String chineseExplanation;
    /**
     * 例句map<英语例句，中文翻译>
     */
    public List<Section> sectionList;

    /**
     * 放在最后的note
     */
    public String finalNote;

}
