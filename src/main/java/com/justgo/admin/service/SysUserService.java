package com.justgo.admin.service;

import com.justgo.admin.dto.base.BaseResponseDTO;
import com.justgo.admin.dto.base.BootstrapTableResDTO;
import com.justgo.admin.dto.security.user.AddUserDTO;
import com.justgo.admin.dto.security.user.UserDTO;
import com.justgo.admin.dto.security.user.UserListReqDTO;
import com.justgo.admin.dto.security.user.UserRequestDTO;
import com.justgo.admin.entity.SysUser;
import com.justgo.admin.mapper.SysUserMapper;


import java.util.List;

/**
 * author : LYS
 * date : 2017/12/24
 */
public interface SysUserService extends BaseService<SysUser,Long,SysUserMapper>{

    /**
     * 查询用户和角色部分
     * @param param
     * @return
     */
    List<UserDTO> selectUserAndRole(UserRequestDTO param);

    /**
     * 查询用户列表
     * @param dto
     * @return
     */
    BootstrapTableResDTO<UserDTO> userList(UserListReqDTO dto);

    /**
     * 添加用户
     * @param addUserDTO
     * @return
     */
    BaseResponseDTO addUser(AddUserDTO addUserDTO) throws Exception;

    /**
     * 编辑用户
     * @param addUserDTO
     * @return
     */
    BaseResponseDTO editUser(AddUserDTO addUserDTO) throws Exception;

    /**
     * 删除用户
     * @param id
     * @return
     * @throws Exception
     */
    BaseResponseDTO deleteUser(Long id) throws Exception;

    /**
     * 修改密码
     * @param addUserDTO
     * @return
     */
    BaseResponseDTO modifyPassword(AddUserDTO addUserDTO) throws Exception;
}
