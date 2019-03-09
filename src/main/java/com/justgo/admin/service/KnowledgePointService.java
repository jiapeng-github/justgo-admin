package com.justgo.admin.service;

import com.justgo.admin.dto.KnowledgePointDTO;
import com.justgo.admin.dto.base.BaseResponseDTO;
import com.justgo.admin.entity.KnowledgePoint;
import com.justgo.admin.mapper.KnowledgePointMapper;

import java.util.List;


public interface KnowledgePointService extends BaseService<KnowledgePoint, Long, KnowledgePointMapper> {

    /**
     * 根据主键@Id进行删除，多个Id以逗号,分割
     * @param ids
     * @return
     */
    int deleteByIds(String ids);

    BaseResponseDTO create(KnowledgePointDTO knowledgePointDTO) throws Exception;

    BaseResponseDTO modify(KnowledgePointDTO knowledgePointDTO) throws Exception;

    List<KnowledgePoint> selectKnowledgePoint(KnowledgePoint knowledgePoint) throws Exception;

}
