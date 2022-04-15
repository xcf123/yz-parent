package com.yuanzong.utils;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.JSON;
import org.apache.poi.ss.formula.functions.T;


public class PageHelper implements Serializable
{
    private static final long serialVersionUID = -2433032438309225910L;

    private List<T> data;
    
    private int begin;
    
    private int end;
    
    private int length;
    
    private int totalRecords;
    
    private int pageNo;
    
    private int pageCount;
    
    public PageHelper(int begin, int length)
    {
        this.length = 20;
        
        this.begin = begin;
        this.length = length;
        this.end = (this.begin + this.length);
        this.pageNo = ((int)Math.floor(this.begin * 1.0D / this.length) + 1);
    }
    
    public PageHelper(int begin, int length, int totalRecords)
    {
        this(begin, length);
        this.totalRecords = totalRecords;
    }
    
    public PageHelper(int pageNo)
    {
        this.length = 20;
        
        this.pageNo = pageNo;
        pageNo = (pageNo > 0) ? pageNo : 1;
        this.begin = (this.length * (pageNo - 1));
        this.end = (this.length * pageNo);
    }
    
    public int getBegin()
    {
        return this.begin;
    }
    
    public int getEnd()
    {
        return this.end;
    }
    
    public void setEnd(int end)
    {
        this.end = end;
    }
    
    public void setBegin(int begin)
    {
        this.begin = begin;
        if (this.length == 0)
            return;
        this.pageNo = ((int)Math.floor(this.begin * 1.0D / this.length) + 1);
    }
    
    public int getLength()
    {
        return this.length;
    }
    
    public void setLength(int length)
    {
        this.length = length;
        if (this.begin == 0)
            return;
        this.pageNo = ((int)Math.floor(this.begin * 1.0D / this.length) + 1);
    }
    
    public int getTotalRecords()
    {
        return this.totalRecords;
    }
    
    public void setTotalRecords(int totalRecords)
    {
        this.totalRecords = totalRecords;
        this.pageCount = (int)Math.floor(this.totalRecords * 1.0D / this.length);
        if (this.totalRecords % this.length == 0)
            return;
        this.pageCount += 1;
    }
    
    public int getPageNo()
    {
        return this.pageNo;
    }
    
    public void setPageNo(int pageNo)
    {
        this.pageNo = pageNo;
        pageNo = (pageNo > 0) ? pageNo : 1;
        this.begin = (this.length * (pageNo - 1));
        this.end = (this.length * pageNo);
    }
    
    public int getPageCount()
    {
        if (this.pageCount == 0)
        {
            return 1;
        }
        return this.pageCount;
    }
    
    public void setPageCount(int pageCount)
    {
        this.pageCount = pageCount;
    }
    
    public List<T> getData()
    {
        return data;
    }
    
    public void setData(List<T> data)
    {
        this.data = data;
    }
    
    public String toString()
    {
        StringBuilder builder = new StringBuilder("begin=").append(this.begin)
            .append(", end=")
            .append(this.end)
            .append(", length=")
            .append(this.length)
            .append(", totalRecords=")
            .append(this.totalRecords)
            .append(", pageNo=")
            .append(this.pageNo)
            .append(", pageCount=")
            .append(this.pageCount)
            .append(", data=")
            .append(JSON.toJSONString(this.data));
        return builder.toString();
    }
}
