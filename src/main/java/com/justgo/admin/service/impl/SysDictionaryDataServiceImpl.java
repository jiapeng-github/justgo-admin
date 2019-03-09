package com.justgo.admin.service.impl;

import com.fasterxml.jackson.databind.JavaType;
import com.github.pagehelper.util.StringUtil;
import com.justgo.admin.controller.utils.page.PageUtil;
import com.justgo.admin.dto.ServiceSite.SysDictionaryDataDTO;
import com.justgo.admin.entity.SysDictionary;
import com.justgo.admin.entity.SysDictionaryData;
import com.justgo.admin.enums.RedisEnum;
import com.justgo.admin.mapper.SysDictionaryDataMapper;
import com.justgo.admin.service.SysDictionaryDataService;
import com.justgo.admin.service.SysDictionaryService;
import com.justgo.admin.utils.JsonUtils;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fancz on 2017/4/27.
 */
@Service("sysDictionaryDataService")
public class SysDictionaryDataServiceImpl extends BaseServiceImpl<SysDictionaryData,Integer,SysDictionaryDataMapper> implements SysDictionaryDataService {

    @Autowired
    private SysDictionaryDataMapper sysDictionaryDataMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private SysDictionaryService sysDictionaryService;

    @Override
    public int count(SysDictionaryData entityDto) {
        return sysDictionaryDataMapper.count(entityDto);
    }

    @Override
    public List<SysDictionaryData> doQuery(SysDictionaryData entityDto, Integer pageNum, Integer pageSize) {
        Integer pageNumber = pageNum==null?new Integer(1):Integer.valueOf(pageNum);
        Integer rowNum = pageSize==null?new Integer(Integer.MAX_VALUE):Integer.valueOf(pageSize);
        int beginNum = PageUtil.begin(pageNumber, rowNum);
        return sysDictionaryDataMapper.query(entityDto, beginNum,rowNum);
    }

    @Override
    public List<SysDictionaryData> doQueryForOr(SysDictionaryData entityDto, Integer pageNum, Integer pageSize) {
        Integer pageNumber = pageNum==null?new Integer(1):Integer.valueOf(pageNum);
        Integer rowNum = pageSize==null?new Integer(Integer.MAX_VALUE):Integer.valueOf(pageSize);
        int beginNum = PageUtil.begin(pageNumber, rowNum);
        return sysDictionaryDataMapper.queryForOr(entityDto, beginNum,rowNum);
    }

    @Override
    public List<SysDictionaryData> accurateQuery(SysDictionaryData entityDto) {
        return sysDictionaryDataMapper.accurateQuery(entityDto);
    }

    @Override
    public Map<String, SysDictionaryData> getSysDictData(String dictValue) {
        Map<String, SysDictionaryData> result = null;
        String sysDictDataMapString = (String) redisTemplate.opsForHash().get(RedisEnum.REDIS_SYS_DICT_KEY.getCode(), dictValue);
        if (!StringUtil.isEmpty(sysDictDataMapString)) {
            JavaType javaType = JsonUtils.getCollectionType(HashMap.class, String.class, SysDictionaryData.class);
            result = JsonUtils.toObject(sysDictDataMapString,javaType);
        } else {
            //查询数据库
            SysDictionary dto = new SysDictionary();
            dto.setDictValue(dictValue);
            dto.setValid("1");
            SysDictionary sysDictionary = sysDictionaryService.selectOne(dto);
            if (null != sysDictionary) {
                SysDictionaryDataDTO sysDictionaryDataDTO = new SysDictionaryDataDTO();
                sysDictionaryDataDTO.setDictValue(dictValue);
                sysDictionaryDataDTO.setValid("1");
                List<SysDictionaryData> sysDictionaryDatas = sysDictionaryDataMapper.query(sysDictionaryDataDTO, PageUtil.begin(1,Integer.MAX_VALUE),Integer.MAX_VALUE);
                if (null != sysDictionaryDatas && sysDictionaryDatas.size() > 0) {
                    Map<String, SysDictionaryData> sysDictDataMapNew = new HashMap<>();
                    sysDictionaryDatas.stream().forEach(entity -> {
                        sysDictDataMapNew.put(entity.getDictDataName(), entity);
                    });
                    //返回值
                    result = sysDictDataMapNew;
                    //同时把map重新刷到redis
                    redisTemplate.opsForHash().put(RedisEnum.REDIS_SYS_DICT_KEY.getCode(), dictValue, JsonUtils.toJson(sysDictDataMapNew));
                }
            }
        }
        return result;
    }

    @Override
    public SysDictionaryData getSysDictData(String dictValue, String dictDataName) {
        SysDictionaryData result = null;
        String sysDictDataMapString = (String) redisTemplate.opsForHash().get(RedisEnum.REDIS_SYS_DICT_KEY.getCode(), dictValue);
        if (!StringUtil.isEmpty(sysDictDataMapString)) {
            JavaType javaType = JsonUtils.getCollectionType(HashMap.class, String.class, SysDictionaryData.class);
            Map<String,SysDictionaryData> sysDictDataMap = JsonUtils.toObject(sysDictDataMapString,javaType);
            result = sysDictDataMap.get(dictDataName);
        } else {
            //查询数据库
            SysDictionary dto = new SysDictionary();
            dto.setDictValue(dictValue);
            dto.setValid("1");
            SysDictionary sysDictionary = sysDictionaryService.selectOne(dto);
            if (null != sysDictionary) {
                SysDictionaryDataDTO sysDictionaryDataDTO = new SysDictionaryDataDTO();
                sysDictionaryDataDTO.setDictValue(dictValue);
                sysDictionaryDataDTO.setValid("1");
                List<SysDictionaryData> sysDictionaryDatas = sysDictionaryDataMapper.query(sysDictionaryDataDTO,PageUtil.begin(1,Integer.MAX_VALUE),Integer.MAX_VALUE);
                if (null != sysDictionaryDatas && sysDictionaryDatas.size() > 0) {
                    Map<String, SysDictionaryData> sysDictDataMapNew = new HashMap<>();
                    sysDictionaryDatas.stream().forEach(entity -> {
                        sysDictDataMapNew.put(entity.getDictDataName(), entity);
                    });
                    //返回值
                    //if(null == sysDictDataMapNew.get(dictDataName))return null;
                    result = sysDictDataMapNew.get(dictDataName);
                    //同时把map重新刷到redis
                    redisTemplate.opsForHash().put(RedisEnum.REDIS_SYS_DICT_KEY.getCode(), dictValue, JsonUtils.toJson(sysDictDataMapNew));
                }
            }

        }
        return result;
    }

    @Override
    public String getSysDictDataValue(String dictValue, String dictDataName) {
        String result = null;
        String sysDictDataMapString = (String) redisTemplate.opsForHash().get(RedisEnum.REDIS_SYS_DICT_KEY.getCode(), dictValue);
        if (!StringUtil.isEmpty(sysDictDataMapString)) {
            JavaType javaType = JsonUtils.getCollectionType(HashMap.class, String.class, SysDictionaryData.class);
            Map<String,SysDictionaryData> sysDictDataMap = JsonUtils.toObject(sysDictDataMapString,javaType);
            if(null == sysDictDataMap.get(dictDataName))return null;
            result = sysDictDataMap.get(dictDataName).getDictDataValue();
        } else {
            //查询数据库
            SysDictionary dto = new SysDictionary();
            dto.setDictValue(dictValue);
            dto.setValid("1");
            SysDictionary sysDictionary = sysDictionaryService.selectOne(dto);
            if (null != sysDictionary) {
                SysDictionaryDataDTO sysDictionaryDataDTO = new SysDictionaryDataDTO();
                sysDictionaryDataDTO.setDictValue(dictValue);
                sysDictionaryDataDTO.setValid("1");
                List<SysDictionaryData> sysDictionaryDatas = sysDictionaryDataMapper.query(sysDictionaryDataDTO, PageUtil.begin(1,Integer.MAX_VALUE),Integer.MAX_VALUE);
                if (null != sysDictionaryDatas && sysDictionaryDatas.size() > 0) {
                    Map<String, SysDictionaryData> sysDictDataMapNew = new HashMap<>();
                    sysDictionaryDatas.stream().forEach(entity -> {
                        sysDictDataMapNew.put(entity.getDictDataName(), entity);
                    });
                    //返回值
                    if(null == sysDictDataMapNew.get(dictDataName))return null;
                    result = sysDictDataMapNew.get(dictDataName).getDictDataValue();
                    //同时把map重新刷到redis
                    redisTemplate.opsForHash().put(RedisEnum.REDIS_SYS_DICT_KEY.getCode(), dictValue, JsonUtils.toJson(sysDictDataMapNew));
                }
            }

        }
        return result;
    }
}
