package com.justgo.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.justgo.admin.dto.base.BaseResponseDTO;
import com.justgo.admin.dto.base.BootstrapTableResDTO;
import com.justgo.admin.dto.security.role.RoleDTO;
import com.justgo.admin.dto.security.role.RoleListReqDTO;
import com.justgo.admin.entity.SysRole;
import com.justgo.admin.entity.SysRoleResource;
import com.justgo.admin.enums.AdminResponseEnum;
import com.justgo.admin.mapper.SysRoleMapper;
import com.justgo.admin.service.SysRoleResourceService;
import com.justgo.admin.service.SysRoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * author : LYS
 * date : 2017/12/24
 */
@Transactional
@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole, Long, SysRoleMapper> implements SysRoleService {
    @Autowired
    private SysRoleResourceService sysRoleResourceService;

    /**
     * 查找角色
     *
     * @return
     */
    @Override
    public List<RoleDTO> selectRoleDTO() {
        return mapper.selectRoleDTO();
    }

    /**
     * 角色List
     *
     * @return
     */
    @Override
    public BootstrapTableResDTO<RoleDTO> roleList(RoleListReqDTO roleListReqDTO) {
        BootstrapTableResDTO<RoleDTO> resDTO = new BootstrapTableResDTO<>();
        Integer pageNumber = null;
        if (roleListReqDTO != null) {
            pageNumber = roleListReqDTO.getPageNumber();
        }
        PageHelper.startPage(pageNumber == null ? 1 : pageNumber, 10);
        List<RoleDTO> roleDTOS = selectRoleDTO();
        PageInfo<RoleDTO> page = new PageInfo<>(roleDTOS);
        resDTO.setTotal(page.getTotal());
        resDTO.setRows(roleDTOS);
        return resDTO;
    }

    /**
     * 增加一个角色
     *
     * @param sysRole
     * @return
     */
    @Override
    public BaseResponseDTO addRole(SysRole sysRole) {
        sysRole.setRoleStatus(1);
        add(sysRole);
        return new BaseResponseDTO(AdminResponseEnum.SUCCESS);
    }

    /**
     * 编辑用户
     *
     * @param sysRole
     * @return
     */
    @Override
    public BaseResponseDTO editRole(SysRole sysRole) throws Exception {
        updateByPrimaryKeySelective(sysRole);
        return new BaseResponseDTO(AdminResponseEnum.SUCCESS);
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public BaseResponseDTO deleteRole(Long id) throws Exception {
        deleteByPrimaryKey(id);
        SysRoleResource sysRoleResource = new SysRoleResource();
        sysRoleResource.setRoleId(id);
        sysRoleResourceService.delete(sysRoleResource);
        return BaseResponseDTO.SUCCESS;
    }
}
