package com.yuanzong.dao;


import com.yuanzong.beans.model.auto.QuestionDef;
import com.yuanzong.beans.model.auto.QuestionDefExample;
import com.yuanzong.beans.model.auto.QuestionDefWithBLOBs;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QuestionDefMapper {
    public static final String DATA_SOURCE_NAME = "null";

    int countByExample(QuestionDefExample example);

    int deleteByExample(QuestionDefExample example);

    int deleteByPrimaryKey(Long id);

    int insert(QuestionDefWithBLOBs record);

    int insertSelective(QuestionDefWithBLOBs record);

    List<QuestionDefWithBLOBs> selectByExampleWithBLOBs(QuestionDefExample example);

    List<QuestionDef> selectByExample(QuestionDefExample example);

    QuestionDefWithBLOBs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") QuestionDefWithBLOBs record, @Param("example") QuestionDefExample
            example);

    int updateByExampleWithBLOBs(@Param("record") QuestionDefWithBLOBs record, @Param("example") QuestionDefExample example);

    int updateByExample(@Param("record") QuestionDef record, @Param("example") QuestionDefExample example);

    int updateByPrimaryKeySelective(QuestionDefWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(QuestionDefWithBLOBs record);

    int updateByPrimaryKey(QuestionDef record);

    int insertBatch(List<QuestionDef> records);
}