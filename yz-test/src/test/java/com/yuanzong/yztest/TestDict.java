package com.yuanzong.yztest;

import java.util.List;
import java.util.Map;

/**
 * @author xiechaofeng
 * @date 2018/7/10 14:41
 * @desc
 */
public class TestDict {
    public static void main(String[] args) {
        ParseBean parseBean=new ParseBean();


    }
}


class ParseBean{
    /**
     * 异型词
     */
    String Shaped;
    /**
     * 单词词性
     */
    String  partOfSpeech;

    List<ExplanationBean> explanationBeanList;

}

class ExplanationBean{
    /**
     * 简单的英语短语解释
     */
    String englishPhrase;
    /**
     * 英文短语翻译
     */
    String chinesePhrase;
    /**
     * 搭配的词性
     */
    String explanationBeanPartOfSpeech;
    List<BaseBean> baseBeanList;
}
class BaseBean{
    /**
     * 英文单词解释
     */
    List<String> englishExplanation;
    /**
     * 中文单词解释
     */
    List<String> chineseExplanation;
    /**
     * 例句map<英语例句，中文翻译>
     */
    Map<String,String> exampleExplanation;

}
