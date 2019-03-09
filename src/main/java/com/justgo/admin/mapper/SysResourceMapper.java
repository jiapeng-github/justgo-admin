package com.justgo.admin.mapper;

import com.justgo.admin.entity.SysResource;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysResourceMapper extends Mapper<SysResource> {

    /**
     * 根据用户id获取资源
     *
     * @param userID
     * @return
     */
    List<SysResource> selectByUserID(@Param("userID") Long userID);

    /**
     * 查找全部都权限字符串的
     *
     * @return
     */
    List<SysResource> selectAllPermissions();

}