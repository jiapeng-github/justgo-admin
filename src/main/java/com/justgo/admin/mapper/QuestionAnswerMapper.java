package com.justgo.admin.mapper;

import com.justgo.admin.entity.KnowledgePoint;
import com.justgo.admin.entity.QuestionAnswer;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface QuestionAnswerMapper extends Mapper<QuestionAnswer>,IdsMapper<QuestionAnswer> {
    List<QuestionAnswer> selectQuestionAnswer(QuestionAnswer questionAnswer);
}