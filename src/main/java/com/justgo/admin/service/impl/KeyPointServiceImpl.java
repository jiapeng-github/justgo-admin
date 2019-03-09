package com.justgo.admin.service.impl;

import com.justgo.admin.dto.KeyPointDTO;
import com.justgo.admin.dto.base.BaseResponseDTO;
import com.justgo.admin.entity.KeyPoint;
import com.justgo.admin.mapper.KeyPointMapper;
import com.justgo.admin.service.KeyPointService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Transactional
@Service
public class KeyPointServiceImpl extends BaseServiceImpl<KeyPoint, Long, KeyPointMapper> implements KeyPointService {

    @Override
    public int deleteByIds(String ids) {
        return mapper.deleteByIds(ids);
    }

    @Override
    public BaseResponseDTO create(KeyPointDTO keyPoint) throws Exception {
        MultipartFile multipartFile = keyPoint.getMultipartFile();

        if (multipartFile != null){
            byte[] file = multipartFile.getBytes();
            String originalFilename = multipartFile.getOriginalFilename();
            keyPoint.setFileName(originalFilename);
            keyPoint.setFile(file);
        }
        add(keyPoint);
        return BaseResponseDTO.SUCCESS;
    }

    @Override
    public BaseResponseDTO modify(KeyPointDTO keyPoint) throws Exception {
        MultipartFile multipartFile = keyPoint.getMultipartFile();

        if (multipartFile != null){
            byte[] file = multipartFile.getBytes();
            String originalFilename = multipartFile.getOriginalFilename();
            keyPoint.setFileName(originalFilename);
            keyPoint.setFile(file);
        }
        updateByPrimaryKeySelective(keyPoint);
        return BaseResponseDTO.SUCCESS;
    }

    @Override
    public List<KeyPoint> selectKeyPoint(KeyPoint knowledgePoint) throws Exception {
        return mapper.selectKeyPoint(knowledgePoint);
    }
}
