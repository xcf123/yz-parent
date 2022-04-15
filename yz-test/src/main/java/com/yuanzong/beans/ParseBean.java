package com.yuanzong.beans;

import java.util.List;

public class ParseBean{

    /**
     * 单词名
     */
   public String name;
    /**
     * 异型词
     */
    public String shaped;
    /**
     * 英式发音
     */
    public String englishVoice;
    /**
     * 其他信息
     */
    public String other;
    /**
     * 单词词性
     */
    public List<String>  partOfSpeechList;

    /**
     * 用法 如way of achieving sth 方法
     */
    public String way;

    public String note;

    public List<ExplanationBean> explanationBeanList;

    public List<PvsBean> pvsBeanList;

}
