package com.justgo.admin.mapper;

import com.justgo.admin.entity.SysDictionaryData;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 系统字典数据表mapper
 * Created by fancz on 2017/4/27.
 */
public interface SysDictionaryDataMapper extends Mapper<SysDictionaryData> {

    /**
     * OR条件类型查询
     */
    public List<SysDictionaryData> queryForOr(@Param("dto") SysDictionaryData entityDto, @Param("beginNum") int beginNum, @Param("rowNum") int rowNum);

    /**
     * 精准查询
     */
    public List<SysDictionaryData> accurateQuery(@Param("dto") SysDictionaryData entityDto);

    public List<SysDictionaryData> query(@Param("dto") SysDictionaryData entityDto, @Param("beginNum") int beginNum, @Param("rowNum") int rowNum);

    public int count(@Param("dto") SysDictionaryData entityDto);
}
