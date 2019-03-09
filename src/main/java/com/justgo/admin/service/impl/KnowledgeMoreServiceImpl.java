package com.justgo.admin.service.impl;

import com.justgo.admin.dto.KnowledgeMoreDTO;
import com.justgo.admin.dto.base.BaseResponseDTO;
import com.justgo.admin.entity.KnowledgeMore;
import com.justgo.admin.mapper.KnowledgeMoreMapper;
import com.justgo.admin.service.KnowledgeMoreService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Transactional
@Service
public class KnowledgeMoreServiceImpl extends BaseServiceImpl<KnowledgeMore, Long, KnowledgeMoreMapper> implements KnowledgeMoreService {

    @Override
    public int deleteByIds(String ids) {
        return mapper.deleteByIds(ids);
    }

    @Override
    public BaseResponseDTO create(KnowledgeMoreDTO knowledgeMoreDTO) throws Exception {
        MultipartFile multipartFile = knowledgeMoreDTO.getMultipartFile();

        if (multipartFile != null){
            byte[] file = multipartFile.getBytes();
            String originalFilename = multipartFile.getOriginalFilename();
            knowledgeMoreDTO.setFileName(originalFilename);
            knowledgeMoreDTO.setFile(file);
        }
        add(knowledgeMoreDTO);
        return BaseResponseDTO.SUCCESS;
    }

    @Override
    public BaseResponseDTO modify(KnowledgeMoreDTO knowledgeMoreDTO) throws Exception {
        MultipartFile multipartFile = knowledgeMoreDTO.getMultipartFile();

        if (multipartFile != null){
            byte[] file = multipartFile.getBytes();
            String originalFilename = multipartFile.getOriginalFilename();
            knowledgeMoreDTO.setFileName(originalFilename);
            knowledgeMoreDTO.setFile(file);
        }
        updateByPrimaryKeySelective(knowledgeMoreDTO);
        return BaseResponseDTO.SUCCESS;
    }

    @Override
    public List<KnowledgeMore> selectKnowledgeMore(KnowledgeMore knowledgeMore) throws Exception {
        return mapper.selectKnowledgeMore(knowledgeMore);
    }
}
