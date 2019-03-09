package com.justgo.admin.service;

import com.justgo.admin.dto.base.BaseResponseDTO;
import com.justgo.admin.dto.base.BootstrapTableResDTO;
import com.justgo.admin.dto.security.role.RoleDTO;
import com.justgo.admin.dto.security.role.RoleListReqDTO;
import com.justgo.admin.entity.SysRole;
import com.justgo.admin.mapper.SysRoleMapper;


import java.util.List;

/**
 * author : LYS
 * date : 2017/12/24
 */
public interface SysRoleService extends BaseService<SysRole, Long, SysRoleMapper> {

    /**
     * 查找角色
     * @return
     */
    List<RoleDTO> selectRoleDTO();

    /**
     * 角色List
     * @return
     */
    BootstrapTableResDTO<RoleDTO> roleList(RoleListReqDTO roleListReqDTO);

    /**
     * 增加一个角色
     * @param sysRole
     * @return
     */
    BaseResponseDTO addRole(SysRole sysRole);

    /**
     * 编辑角色
     * @param sysRole
     * @return
     */
    BaseResponseDTO editRole(SysRole sysRole) throws Exception;

    /**
     * 删除角色
     * @param id
     * @return
     * @throws Exception
     */
    BaseResponseDTO deleteRole(Long id) throws Exception;

}
