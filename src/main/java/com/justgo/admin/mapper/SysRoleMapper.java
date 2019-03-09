package com.justgo.admin.mapper;

import com.justgo.admin.dto.security.role.RoleDTO;
import com.justgo.admin.entity.SysRole;

import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysRoleMapper extends Mapper<SysRole> {

    /**
     * 查找角色
     *
     * @return
     */
    List<RoleDTO> selectRoleDTO();
}