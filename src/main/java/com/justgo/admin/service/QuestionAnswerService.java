package com.justgo.admin.service;

import com.justgo.admin.dto.QuestionAnswerDTO;
import com.justgo.admin.dto.base.BaseResponseDTO;
import com.justgo.admin.entity.QuestionAnswer;
import com.justgo.admin.mapper.QuestionAnswerMapper;

import java.util.List;


public interface QuestionAnswerService extends BaseService<QuestionAnswer, Long, QuestionAnswerMapper> {
    /**
     * 根据主键@Id进行删除，多个Id以逗号,分割
     * @param ids
     * @return
     */
    int deleteByIds(String ids);

    BaseResponseDTO create(QuestionAnswerDTO questionAnswerDTO) throws Exception;

    BaseResponseDTO modify(QuestionAnswerDTO questionAnswerDTO) throws Exception;

    List<QuestionAnswer> selectQuestionAnswer(QuestionAnswer questionAnswer) throws Exception;
}
