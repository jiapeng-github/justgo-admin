package com.justgo.admin.mapper;

import com.justgo.admin.entity.SysDictionary;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 系统字典表mapper
 * Created by fancz on 2017/4/27.
 */
public interface SysDictionaryMapper extends Mapper<SysDictionary> {

    /**
     * OR条件类型查询
     */
    public List<SysDictionary> queryForOr(@Param("dto") SysDictionary entityDto, @Param("beginNum") int beginNum, @Param("rowNum") int rowNum);

    public List<SysDictionary> query(@Param("dto") SysDictionary entityDto, @Param("beginNum") int beginNum, @Param("rowNum") int rowNum);

    public int count(@Param("dto") SysDictionary entityDto);
}
