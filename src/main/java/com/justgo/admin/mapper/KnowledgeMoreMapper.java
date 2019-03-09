package com.justgo.admin.mapper;

import com.justgo.admin.entity.KnowledgeMore;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface KnowledgeMoreMapper extends Mapper<KnowledgeMore>,IdsMapper<KnowledgeMore> {

    List<KnowledgeMore> selectKnowledgeMore(KnowledgeMore knowledgeMore);

}