package com.justgo.admin.service.impl;

import com.justgo.admin.dto.KnowledgePointDTO;
import com.justgo.admin.dto.QuestionAnswerDTO;
import com.justgo.admin.dto.base.BaseResponseDTO;
import com.justgo.admin.entity.KnowledgePoint;
import com.justgo.admin.entity.QuestionAnswer;
import com.justgo.admin.mapper.KnowledgePointMapper;
import com.justgo.admin.mapper.QuestionAnswerMapper;
import com.justgo.admin.service.KnowledgePointService;
import com.justgo.admin.service.QuestionAnswerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Transactional
@Service
public class QuestionAnswerServiceImpl extends BaseServiceImpl<QuestionAnswer, Long, QuestionAnswerMapper> implements QuestionAnswerService {

    @Override
    public int deleteByIds(String ids) {
        return mapper.deleteByIds(ids);
    }

    @Override
    public BaseResponseDTO create(QuestionAnswerDTO questionAnswerDTO) throws Exception {
        MultipartFile multipartFile = questionAnswerDTO.getMultipartFile();

        if (multipartFile != null){
            byte[] file = multipartFile.getBytes();
            String originalFilename = multipartFile.getOriginalFilename();
            questionAnswerDTO.setFileName(originalFilename);
            questionAnswerDTO.setFile(file);
        }
        add(questionAnswerDTO);
        return BaseResponseDTO.SUCCESS;
    }

    @Override
    public BaseResponseDTO modify(QuestionAnswerDTO questionAnswerDTO) throws Exception {
        MultipartFile multipartFile = questionAnswerDTO.getMultipartFile();

        if (multipartFile != null){
            byte[] file = multipartFile.getBytes();
            String originalFilename = multipartFile.getOriginalFilename();
            questionAnswerDTO.setFileName(originalFilename);
            questionAnswerDTO.setFile(file);
        }
        updateByPrimaryKeySelective(questionAnswerDTO);
        return BaseResponseDTO.SUCCESS;
    }

    @Override
    public List<QuestionAnswer> selectQuestionAnswer(QuestionAnswer questionAnswer) throws Exception {
        return mapper.selectQuestionAnswer(questionAnswer);
    }
}
