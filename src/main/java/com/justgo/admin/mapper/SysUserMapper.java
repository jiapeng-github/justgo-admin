package com.justgo.admin.mapper;


import com.justgo.admin.dto.security.user.UserDTO;
import com.justgo.admin.dto.security.user.UserRequestDTO;
import com.justgo.admin.entity.SysUser;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysUserMapper extends Mapper<SysUser> {
    /**
     * 查询用户和角色部分
     *
     * @param param
     * @return
     */
    List<UserDTO> selectUserAndRole(UserRequestDTO param);
}