package com.justgo.admin.service;

import com.justgo.admin.dto.base.BaseResponseDTO;
import com.justgo.admin.dto.base.BootstrapTableResDTO;
import com.justgo.admin.dto.base.BootstrapTreeDTO;
import com.justgo.admin.dto.security.resources.ResourcesListReqDTO;
import com.justgo.admin.entity.SysResource;
import com.justgo.admin.mapper.SysResourceMapper;


import java.util.List;

/**
 * 资源
 * author : LYS
 * date : 2017/12/24
 */
public interface SysResourceService extends BaseService<SysResource,Long,SysResourceMapper>{

    /**
     * 根据用户id获取资源
     * @param userID
     * @return
     */
    List<SysResource> selectByUserID(Long userID);

    /**
     * 获取根据roleID获取全部资源(包含未分配的资源)
     * @param roleID
     * @return
     */
    List<BootstrapTreeDTO> getALLResource(Long roleID);

    /**
     * 授权
     * @param roleID  授权角色ID
     * @param resourcesID
     * @return
     */
    BaseResponseDTO authorization(Long roleID, Long[] resourcesID);

    /**
     * 查找全部都权限字符串的
     * @return
     */
    List<SysResource> selectAllPermissions();

    /**
     * 根据类型获取父资源
     * @return
     */
    BaseResponseDTO<List<SysResource>> getResourceFidByType(Integer resourcesType);

    /**
     * 增加资源
     * @param sysResource
     * @return
     */
    BaseResponseDTO addResource(SysResource sysResource);

    /**
     * 获取资源
     * @param reqDTO
     * @return
     */
    BootstrapTableResDTO<SysResource> getResources(ResourcesListReqDTO reqDTO);

    /**
     * 更新资源
     * @param sysResource
     * @return
     */
    BaseResponseDTO updateResource(SysResource sysResource);
}
