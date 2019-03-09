package com.justgo.admin.service;

import com.justgo.admin.dto.KnowledgeMoreDTO;
import com.justgo.admin.dto.base.BaseResponseDTO;
import com.justgo.admin.entity.KnowledgeMore;
import com.justgo.admin.mapper.KnowledgeMoreMapper;

import java.util.List;


public interface KnowledgeMoreService extends BaseService<KnowledgeMore, Long, KnowledgeMoreMapper> {

    /**
     * 根据主键@Id进行删除，多个Id以逗号,分割
     * @param ids
     * @return
     */
    int deleteByIds(String ids);

    BaseResponseDTO create(KnowledgeMoreDTO knowledgeMoreDTO) throws Exception;

    BaseResponseDTO modify(KnowledgeMoreDTO knowledgeMoreDTO) throws Exception;

    List<KnowledgeMore> selectKnowledgeMore(KnowledgeMore knowledgeMore) throws Exception;

}
