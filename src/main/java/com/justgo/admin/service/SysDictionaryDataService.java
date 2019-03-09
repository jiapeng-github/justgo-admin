package com.justgo.admin.service;



import com.justgo.admin.entity.SysDictionaryData;
import com.justgo.admin.mapper.SysDictionaryDataMapper;

import java.util.List;
import java.util.Map;

/**
 * 系统字典数据表service
 * Created by fancz on 2017/4/27.
 */
public interface SysDictionaryDataService extends BaseService<SysDictionaryData,Integer,SysDictionaryDataMapper> {


    /**
     * 查询记录翻页
     */
    public int count(SysDictionaryData entityDto);
    public List<SysDictionaryData> doQuery(SysDictionaryData entityDto, Integer pageNum, Integer pageSize);
    /**
     * 查询记录列表（or类型查询）
     */
    public List<SysDictionaryData> doQueryForOr(SysDictionaryData entityDto, Integer pageNum, Integer pageSize);
    /**
     * 精准查询
     */
    public List<SysDictionaryData> accurateQuery(SysDictionaryData entityDto);

    /**
     * 获取某个系统字典的所有字典数据(Map里存的是sysDictionaryData的jsonString串)
     */
    public Map<String,SysDictionaryData> getSysDictData(String dictValue);

    /**
     * 获取某个系统字典的某个字典数据
     */
    public SysDictionaryData getSysDictData(String dictValue, String dictDataName);

    /**
     * 获取某个系统字典的某个字典数据值
     */
    public String getSysDictDataValue(String dictValue, String dictDataName);



}
