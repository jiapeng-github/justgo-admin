package com.justgo.admin.mapper;

import com.justgo.admin.entity.KeyPoint;
import com.justgo.admin.entity.KnowledgePoint;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface KeyPointMapper extends Mapper<KeyPoint>,IdsMapper<KeyPoint> {

    List<KeyPoint> selectKeyPoint(KeyPoint keyPoint);

}