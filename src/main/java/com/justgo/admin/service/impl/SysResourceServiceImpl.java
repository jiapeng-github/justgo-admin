package com.justgo.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.justgo.admin.dto.base.BaseResponseDTO;
import com.justgo.admin.dto.base.BootstrapTableResDTO;
import com.justgo.admin.dto.base.BootstrapTreeDTO;
import com.justgo.admin.dto.base.BootstrapTreeStateDTO;
import com.justgo.admin.dto.security.resources.ResourcesListReqDTO;
import com.justgo.admin.entity.SysResource;
import com.justgo.admin.entity.SysRole;
import com.justgo.admin.entity.SysRoleResource;
import com.justgo.admin.enums.AdminResponseEnum;
import com.justgo.admin.mapper.SysResourceMapper;
import com.justgo.admin.service.SysResourceService;
import com.justgo.admin.service.SysRoleResourceService;
import com.justgo.admin.service.SysRoleService;
import com.justgo.admin.service.SysUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.Weekend;
import tk.mybatis.mapper.weekend.WeekendCriteria;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * author : LYS
 */
@Service
@Transactional
public class SysResourceServiceImpl extends BaseServiceImpl<SysResource, Long, SysResourceMapper> implements SysResourceService {

    @Autowired
    private SysRoleResourceService sysRoleResourceService;

    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysUserService sysUserService;

    /**
     * 根据用户id获取资源
     *
     * @param userID
     * @return
     */
    @Override
    public List<SysResource> selectByUserID(Long userID) {
        return mapper.selectByUserID(userID);
    }

    /**
     * 获取根据roleID获取全部资源(包含未分配的资源)
     *
     * @param roleID
     * @return
     */
    @Override
    public List<BootstrapTreeDTO> getALLResource(Long roleID) {
        List<SysResource> sysResources = selectAll();
        List<BootstrapTreeDTO> bootstrapTreeDTOS = new ArrayList<>();
        if (sysResources.isEmpty()) {
            return bootstrapTreeDTOS;
        }

        SysRoleResource sysRoleResource = new SysRoleResource();
        sysRoleResource.setRoleId(roleID);
        List<SysRoleResource> roleResources = sysRoleResourceService.select(sysRoleResource);

        // TODO: 2018/1/8 之后要改成递归方式实现
        //获取一级菜单
        List<SysResource> oneSysResources = getLevelResource(sysResources, 1);
        //初始化一级菜单
        setTreeDate(oneSysResources, bootstrapTreeDTOS);

        List<BootstrapTreeDTO> secondTreeDTOS = new ArrayList<>();
        //获取二级菜单  second
        List<SysResource> secondSysResources = getLevelResource(sysResources, 2);
        //初始化二级菜单
        setTreeDate(secondSysResources, secondTreeDTOS);

        //获取三级菜单  thrift
        List<SysResource> thriftSysResources = getLevelResource(sysResources, 3);
        //把三级菜单设置到二级菜单中
        secondTreeDTOS.forEach(_bootstrapTreeDTO -> thriftSysResources.forEach(sysResource -> {
            if (sysResource.getFId().equals(_bootstrapTreeDTO.getId())) {
                if (_bootstrapTreeDTO.getNodes() == null) _bootstrapTreeDTO.setNodes(new ArrayList<>());
                BootstrapTreeDTO bootstrapTreeDTO = new BootstrapTreeDTO();
                bootstrapTreeDTO.setText(sysResource.getResourceName());
                bootstrapTreeDTO.setId(sysResource.getId());
                bootstrapTreeDTO.setState(new BootstrapTreeStateDTO());
                _bootstrapTreeDTO.getNodes().add(bootstrapTreeDTO);
            }
        }));

        //把二级菜单设置到一级菜单中
        bootstrapTreeDTOS.forEach(bootstrapTreeDTO -> secondTreeDTOS.forEach(bootstrapTreeDTO1 -> {
            if (bootstrapTreeDTO1.getFid().equals(bootstrapTreeDTO.getId())) {
                if (bootstrapTreeDTO.getNodes() == null) bootstrapTreeDTO.setNodes(new ArrayList<>());
                bootstrapTreeDTO.getNodes().add(bootstrapTreeDTO1);
            }
        }));

        setOwnAuthority(bootstrapTreeDTOS, roleResources);


        return bootstrapTreeDTOS;
    }

    /**
     * 授权
     *
     * @param roleID      授权角色ID
     * @param resourcesID
     * @return
     */
    @Override
    public BaseResponseDTO authorization(Long roleID, Long[] resourcesID) {

        SysRole sysRole = sysRoleService.selectByPrimaryKey(roleID);
        if (sysRole == null) return new BaseResponseDTO(AdminResponseEnum.ROLE_NOT_EXIST);

        //删除之前的授权
        SysRoleResource sysRoleResource = new SysRoleResource();
        sysRoleResource.setRoleId(roleID);
        sysRoleResourceService.delete(sysRoleResource);


        Arrays.asList(resourcesID).forEach(s -> {
            SysRoleResource _sysRoleResource = new SysRoleResource();
            _sysRoleResource.setRoleId(roleID);
            _sysRoleResource.setResourceId(s);
            sysRoleResourceService.add(_sysRoleResource);
        });
        return BaseResponseDTO.SUCCESS;
    }

    /**
     * 查找全部都权限字符串的
     *
     * @return
     */
    @Override
    public List<SysResource> selectAllPermissions() {
        return mapper.selectAllPermissions();
    }

    /**
     * 根据类型获取父资源
     *
     * @param resourcesType
     * @return
     */
    @Override
    public BaseResponseDTO<List<SysResource>> getResourceFidByType(Integer resourcesType) {
        List<SysResource> data = new ArrayList<>();
        Weekend<SysResource> weekend = new Weekend<>(SysResource.class);
        WeekendCriteria<SysResource, Object> criteria = weekend.weekendCriteria();
        switch (resourcesType) {
            case 1:
                return new BaseResponseDTO<>(data, AdminResponseEnum.SUCCESS);
            case 2:
                criteria.andEqualTo(SysResource::getResourceType, 1);
                break;
            case 3:
                criteria.andEqualTo(SysResource::getResourceType, 2);
                break;
        }

        data = selectByExample(weekend);

        return new BaseResponseDTO<>(data, AdminResponseEnum.SUCCESS);
    }

    /**
     * 增加资源
     *
     * @param sysResource
     * @return
     */
    @Override
    public BaseResponseDTO addResource(SysResource sysResource) {
        Integer resourceType = sysResource.getResourceType();
        Long fId = sysResource.getFId();
        Integer priority = 0;
        Example example = new Example(SysResource.class);
        Example.Criteria criteria = example.createCriteria();
        // 获取排序
        switch (resourceType) {
            case 1:
                criteria.andEqualTo("resourceType", 1);
                break;
            case 2:
                criteria.andEqualTo("resourceType", 2);
                criteria.andEqualTo("fId", fId);
                sysResource.setIcon(null);
                break;
            case 3:
                criteria.andEqualTo("resourceType", 3);
                criteria.andEqualTo("fId", fId);
                sysResource.setIcon(null);
                break;
        }

        example.orderBy("priority").desc();
        PageHelper.startPage(1, 1);
        List<SysResource> sysResources = selectByExample(example);
        if (!sysResources.isEmpty()) {
            priority = sysResources.get(0).getPriority() + 1;
        }
        sysResource.setPriority(priority);
        sysResource.setResourceStatus(sysResource.getResourceStatus());
        add(sysResource);

        return BaseResponseDTO.SUCCESS;
    }

    /**
     * 获取资源
     *
     * @param reqDTO
     * @return
     */
    @Override
    public BootstrapTableResDTO<SysResource> getResources(ResourcesListReqDTO reqDTO) {

        BootstrapTableResDTO<SysResource> responseDTO = new BootstrapTableResDTO<>();

        Long id = reqDTO.getId();
        if (id != null){
            SysResource sysResource = selectByPrimaryKey(id);
            responseDTO.setTotal(1L);
            responseDTO.setRows(Collections.singletonList(sysResource));
            return responseDTO;
        }

        Long fId = reqDTO.getFId();
        SysResource _sysResource = new SysResource();
        if (fId == null) {
            _sysResource.setResourceType(1);
        }
        _sysResource.setFId(fId);
        Integer pageNumber = reqDTO.getPageNumber();
        if (reqDTO.getIsShowDetail() == 1) {
            pageNumber = 1;
        }
        PageHelper.startPage(pageNumber, 10);
        List<SysResource> select = select(_sysResource);
        PageInfo<SysResource> pageInfo = new PageInfo<>(select);
        responseDTO.setRows(select);
        responseDTO.setTotal(pageInfo.getTotal());

        return responseDTO;
    }

    /**
     * 更新资源
     *
     * @param sysResource
     * @return
     */
    @Override
    public BaseResponseDTO updateResource(SysResource sysResource) {
        Integer resourceType = sysResource.getResourceType();
        if (resourceType == 1){
            sysResource.setFId(null);
            sysResource.setResourceUrl(null);
            sysResource.setPermission(null);
        }else {
            sysResource.setIcon(null);
        }
        updateByPrimaryKeySelective(sysResource);
        return BaseResponseDTO.SUCCESS;
    }

    /**
     * 设置role对应的权限
     *
     * @param bootstrapTreeDTOS
     * @param roleResources
     */
    public void setOwnAuthority(List<BootstrapTreeDTO> bootstrapTreeDTOS, List<SysRoleResource> roleResources) {
        bootstrapTreeDTOS.forEach(bootstrapTreeDTO -> roleResources.forEach(sysRoleResource -> {
            if (bootstrapTreeDTO.getId().equals(sysRoleResource.getResourceId())) {
                bootstrapTreeDTO.getState().setChecked(true);
            }

            List<BootstrapTreeDTO> nodes = bootstrapTreeDTO.getNodes();
            if (nodes != null && !nodes.isEmpty()) {
                setOwnAuthority(nodes, roleResources);
            }

        }));


    }

    /**
     * 获取等级
     *
     * @return
     */
    private List<SysResource> getLevelResource(List<SysResource> sysResources, final Integer level) {
        return sysResources.stream().filter(sysResource -> sysResource.getResourceType().equals(level)).collect(Collectors.toList());
    }

    private void setTreeDate(List<SysResource> sysResources, List<BootstrapTreeDTO> bootstrapTreeDTOS) {
        sysResources.forEach(sysResource -> {
            BootstrapTreeDTO bootstrapTreeDTO = new BootstrapTreeDTO();
            bootstrapTreeDTO.setText(sysResource.getResourceName());
            bootstrapTreeDTO.setId(sysResource.getId());
            bootstrapTreeDTO.setFid(sysResource.getFId());
            bootstrapTreeDTO.setState(new BootstrapTreeStateDTO());
            // bootstrapTreeDTO.setNodes(new ArrayList<>());
            bootstrapTreeDTOS.add(bootstrapTreeDTO);
        });
    }
}
