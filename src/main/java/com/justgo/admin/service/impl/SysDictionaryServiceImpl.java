package com.justgo.admin.service.impl;

import com.justgo.admin.controller.utils.page.PageUtil;
import com.justgo.admin.entity.SysDictionary;
import com.justgo.admin.mapper.SysDictionaryMapper;
import com.justgo.admin.service.SysDictionaryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fancz on 2017/4/27.
 */
@Service("sysDictionaryService")
public class SysDictionaryServiceImpl extends BaseServiceImpl<SysDictionary,Integer,SysDictionaryMapper> implements SysDictionaryService {

    @Autowired
    private SysDictionaryMapper sysDictionaryMapper;

    @Override
    public int count(SysDictionary entityDto) {
        int count = sysDictionaryMapper.count(entityDto);
        return count;
    }

    @Override
    public List<SysDictionary> doQuery(SysDictionary entityDto, Integer pageNum, Integer pageSize) {
        Integer pageNumber = pageNum==null?new Integer(1):Integer.valueOf(pageNum);
        Integer rowNum = pageSize==null?new Integer(Integer.MAX_VALUE):Integer.valueOf(pageSize);
        int beginNum = PageUtil.begin(pageNumber, rowNum);
        List<SysDictionary> list= sysDictionaryMapper.query(entityDto, beginNum,rowNum);
        return list;
    }
    @Override
    public List<SysDictionary> doQueryForOr(SysDictionary entityDto,Integer pageNum, Integer pageSize) {
        Integer pageNumber = pageNum==null?new Integer(1):Integer.valueOf(pageNum);
        Integer rowNum = pageSize==null?new Integer(Integer.MAX_VALUE):Integer.valueOf(pageSize);
        int beginNum = PageUtil.begin(pageNumber, rowNum);
        return sysDictionaryMapper.queryForOr(entityDto,beginNum,rowNum);
    }
}
