package com.yuanzong.beans.model;

import java.io.Serializable;
import java.util.Date;

public class QuestionModel implements Serializable
{
    private static final long serialVersionUID = 4045078156211350271L;

    private Integer questionId;

    private Integer itemSubjectId;

    private Integer itemTypeId;

    private Integer status;

    private String questionTitle;

    private Integer nodeId;

    /** question 表中的creattype，1表示管理员，2表示教师*/
    private String createId;

    private Integer createType;

    private Date createTime;

    private Date lastUpdateTime;

    private Integer correctType;

    private Integer bookId;

    private String sortVal;

    private Integer videoId;

    public Integer getVideoId()
    {
        return videoId;
    }

    public void setVideoId(Integer videoId)
    {
        this.videoId = videoId;
    }

    public String getSortVal()
    {
        return sortVal;
    }

    public void setSortVal(String sortVal)
    {
        this.sortVal = sortVal;
    }

    public Integer getBookId()
    {
        return bookId;
    }

    public void setBookId(Integer bookId)
    {
        this.bookId = bookId;
    }

    public Integer getCorrectType()
    {
        return correctType;
    }

    public void setCorrectType(Integer correctType)
    {
        this.correctType = correctType;
    }

    public Integer getQuestionId()
    {
        return questionId;
    }

    public void setQuestionId(Integer questionId)
    {
        this.questionId = questionId;
    }


    public Integer getItemSubjectId()
    {
        return itemSubjectId;
    }

    public void setItemSubjectId(Integer itemSubjectId)
    {
        this.itemSubjectId = itemSubjectId;
    }

    public Integer getItemTypeId()
    {
        return itemTypeId;
    }

    public void setItemTypeId(Integer itemTypeId)
    {
        this.itemTypeId = itemTypeId;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getCreateType()
    {
        return createType;
    }

    public void setCreateType(Integer createType)
    {
        this.createType = createType;
    }

    public String getCreateId()
    {
        return createId;
    }

    public void setCreateId(String createId)
    {
        this.createId = createId;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime()
    {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime)
    {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Integer getNodeId()
    {
        return nodeId;
    }

    public void setNodeId(Integer nodeId)
    {
        this.nodeId = nodeId;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }
}
