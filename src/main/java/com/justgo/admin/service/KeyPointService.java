package com.justgo.admin.service;

import com.justgo.admin.dto.KeyPointDTO;
import com.justgo.admin.dto.KnowledgePointDTO;
import com.justgo.admin.dto.base.BaseResponseDTO;
import com.justgo.admin.entity.KeyPoint;
import com.justgo.admin.mapper.KeyPointMapper;
import com.justgo.admin.mapper.KnowledgePointMapper;

import java.util.List;


public interface KeyPointService extends BaseService<KeyPoint, Long, KeyPointMapper> {

    /**
     * 根据主键@Id进行删除，多个Id以逗号,分割
     * @param ids
     * @return
     */
    int deleteByIds(String ids);

    BaseResponseDTO create(KeyPointDTO keyPoint) throws Exception;

    BaseResponseDTO modify(KeyPointDTO keyPoint) throws Exception;

    List<KeyPoint> selectKeyPoint(KeyPoint keyPoint) throws Exception;

}
