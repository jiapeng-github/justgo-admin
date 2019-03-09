package com.justgo.admin.service.impl;

import com.justgo.admin.entity.SysUserRole;
import com.justgo.admin.mapper.SysUserRoleMapper;
import com.justgo.admin.service.SysUserRoleService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * author : LYS
 * date : 2018/1/5 下午2:25
 */
@Service
@Transactional
public class SysUserRoleServiceImpl extends BaseServiceImpl<SysUserRole, Long, SysUserRoleMapper> implements SysUserRoleService {

}
