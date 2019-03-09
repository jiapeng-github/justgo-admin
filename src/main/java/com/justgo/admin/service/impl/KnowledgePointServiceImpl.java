package com.justgo.admin.service.impl;

import com.justgo.admin.dto.KnowledgePointDTO;
import com.justgo.admin.dto.base.BaseResponseDTO;
import com.justgo.admin.entity.KnowledgePoint;
import com.justgo.admin.mapper.KnowledgePointMapper;
import com.justgo.admin.service.KnowledgePointService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Transactional
@Service
public class KnowledgePointServiceImpl extends BaseServiceImpl<KnowledgePoint, Long, KnowledgePointMapper> implements KnowledgePointService {

    @Override
    public int deleteByIds(String ids) {
        return mapper.deleteByIds(ids);
    }

    @Override
    public BaseResponseDTO create(KnowledgePointDTO knowledgePointDTO) throws Exception {
        MultipartFile multipartFile = knowledgePointDTO.getMultipartFile();

        if (multipartFile != null){
            byte[] file = multipartFile.getBytes();
            String originalFilename = multipartFile.getOriginalFilename();
            knowledgePointDTO.setFileName(originalFilename);
            knowledgePointDTO.setFile(file);
        }
        add(knowledgePointDTO);
        return BaseResponseDTO.SUCCESS;
    }

    @Override
    public BaseResponseDTO modify(KnowledgePointDTO knowledgePointDTO) throws Exception {
        MultipartFile multipartFile = knowledgePointDTO.getMultipartFile();

        if (multipartFile != null){
            byte[] file = multipartFile.getBytes();
            String originalFilename = multipartFile.getOriginalFilename();
            knowledgePointDTO.setFileName(originalFilename);
            knowledgePointDTO.setFile(file);
        }
        updateByPrimaryKeySelective(knowledgePointDTO);
        return BaseResponseDTO.SUCCESS;
    }

    @Override
    public List<KnowledgePoint> selectKnowledgePoint(KnowledgePoint knowledgePoint) throws Exception {
        return mapper.selectKnowledgePoint(knowledgePoint);
    }
}
