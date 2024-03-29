package com.yuanzong.beans.cultureBeans;

import java.util.List;

/**
 * @author xiechaofeng
 * @date 2018/7/11 11:15
 * @desc
 */
public class CEnglishBaseBean {
    /**
     * 着重部分，缩写全称 ff标签
     */
    public String abbreviation;

    /**
     * 全名
     */
    public String entireName;
    /**
     * 语法信息[C]
     */
    public List<String> squareBracketsList;

    /**
     * 注解 例如(usu disapprov)
     */

    public  String annotation;

    /**
     * 括号部分 ds标签
     */
    public String brackets;
    /**
     * 详见部分
     */
    public List<String> seeDetailList;

    /**
     * 英文
     */
    public String english;
    /**
     * 英语例句
     */
    public List<String> exampleSentenceList;

    /**
     * 歌词
     */
    public List<String> lyricsList;

}
