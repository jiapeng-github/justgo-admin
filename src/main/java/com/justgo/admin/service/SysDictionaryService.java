package com.justgo.admin.service;




import com.justgo.admin.entity.SysDictionary;
import com.justgo.admin.mapper.SysDictionaryMapper;

import java.util.List;

/**
 * 系统字典表service
 * Created by fancz on 2017/4/27.
 */
public interface SysDictionaryService extends BaseService<SysDictionary,Integer,SysDictionaryMapper> {

    /**
     * 查询记录翻页
     */
    public int count(SysDictionary entityDto);
    public List<SysDictionary> doQuery(SysDictionary entityDto, Integer pageNum, Integer pageSize);

    /**
     * 查询记录列表（or类型查询）
     */
    public List<SysDictionary> doQueryForOr(SysDictionary entityDto, Integer pageNum, Integer pageSize);
}
