package com.yuanzong.beans;

import java.util.List;

public class ExplanationBean{
    /**
     * 简单的英语短语解释
     */
    public String englishPhrase;
    /**
     * 英文短语翻译
     */
    public String chinesePhrase;

    /**
     * 解释的标注
     */
    public String explainNote;
    /**
     * map<搭配的词性,bean>
     */
    public List<BaseBean> baseBeanList;

}
