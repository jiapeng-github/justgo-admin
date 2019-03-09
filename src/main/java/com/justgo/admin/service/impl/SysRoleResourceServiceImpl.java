package com.justgo.admin.service.impl;

import com.justgo.admin.entity.SysRoleResource;
import com.justgo.admin.mapper.SysRoleResourceMapper;
import com.justgo.admin.service.SysRoleResourceService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * author : LYS
 * date : 2018/1/5 下午2:22
 */
@Service
@Transactional
public class SysRoleResourceServiceImpl extends BaseServiceImpl<SysRoleResource, Long, SysRoleResourceMapper> implements SysRoleResourceService {

}
