package com.justgo.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.justgo.admin.dto.base.BaseResponseDTO;
import com.justgo.admin.dto.base.BootstrapTableResDTO;
import com.justgo.admin.dto.security.user.AddUserDTO;
import com.justgo.admin.dto.security.user.UserDTO;
import com.justgo.admin.dto.security.user.UserListReqDTO;
import com.justgo.admin.dto.security.user.UserRequestDTO;
import com.justgo.admin.entity.SysRole;
import com.justgo.admin.entity.SysUser;
import com.justgo.admin.entity.SysUserRole;
import com.justgo.admin.enums.AdminResponseEnum;
import com.justgo.admin.mapper.SysUserMapper;
import com.justgo.admin.service.SysRoleService;
import com.justgo.admin.service.SysUserRoleService;
import com.justgo.admin.service.SysUserService;
import com.justgo.admin.shrio.PasswordHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

import static com.justgo.admin.enums.AdminResponseEnum.ROLE_NOT_EXIST;
import static com.justgo.admin.enums.AdminResponseEnum.USER_EXIST;

/**
 * 用户
 * author : LYS
 * date : 2017/12/24
 */
@Service
@Transactional
public class SysUserServiceImpl extends BaseServiceImpl<SysUser,Long,SysUserMapper> implements SysUserService {

    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysRoleService sysRoleService;
    /**
     * 查询用户和角色部分
     *
     * @param param
     * @return
     */
    @Override
    public List<UserDTO> selectUserAndRole(UserRequestDTO param) {
        return mapper.selectUserAndRole(param);
    }

    /**
     * 查询用户列表
     *
     * @param dto
     * @return
     */
    @Override
    public BootstrapTableResDTO<UserDTO> userList(UserListReqDTO dto) {
        UserRequestDTO param = new UserRequestDTO();
        Integer pageNumber = null;
        if (dto != null) {
            param.setUserName(dto.getUserName());
            pageNumber = dto.getPageNumber();
        }
        PageHelper.startPage(pageNumber == null ? 1 : pageNumber, 10);
        List<UserDTO> userDTOS = selectUserAndRole(param);
        PageInfo<UserDTO> page = new PageInfo<>(userDTOS);
        BootstrapTableResDTO<UserDTO> resDTO = new BootstrapTableResDTO<>();
        resDTO.setTotal(page.getTotal());
        resDTO.setRows(userDTOS);
        return resDTO;
    }

    /**
     * 添加用户
     *
     * @param addUserDTO
     * @return
     */
    @Override
    public BaseResponseDTO addUser(AddUserDTO addUserDTO) throws Exception{
        //查询角色id是否存在
        Long roleID = addUserDTO.getRoleID();
        if (roleID == null) return new BaseResponseDTO(ROLE_NOT_EXIST);
        SysRole sysRole = sysRoleService.selectByPrimaryKey(roleID);
        if (sysRole==null) return new BaseResponseDTO(ROLE_NOT_EXIST);

        //判断用户是否存在
        String userName = addUserDTO.getUserName();
        Example example = new Example(SysUser.class);
        example.createCriteria().andEqualTo("userName", userName.trim());
        List<SysUser> sysUsers = selectByExample(example);
        if (!sysUsers.isEmpty()) new BaseResponseDTO(USER_EXIST);

        SysUser sysUser = new SysUser();
        sysUser.setUserName(userName);
        sysUser.setFullName(addUserDTO.getFullName());
        sysUser.setCreateTime(new Date());
        String salt = PasswordHelper.generatorRandomNumber();
        sysUser.setUserPassword(PasswordHelper.encodePassword(addUserDTO.getPassword(),salt));
        sysUser.setSalt(salt);
        sysUser.setUserStatus(1);
        add(sysUser);

        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setRoleId(roleID);
        sysUserRole.setUserId(sysUser.getId());
        sysUserRoleService.add(sysUserRole);

        return BaseResponseDTO.SUCCESS;
    }

    /**
     * 编辑用户
     *
     * @param addUserDTO
     * @return
     */
    @Override
    public BaseResponseDTO editUser(AddUserDTO addUserDTO) throws Exception {
        SysUser sysUser = new SysUser();
        sysUser.setId(addUserDTO.getUserID());
        sysUser.setUserName(addUserDTO.getUserName());
        sysUser.setFullName(addUserDTO.getFullName());
        if (!"".equals(addUserDTO.getPassword())) {
            String salt = PasswordHelper.generatorRandomNumber();
            sysUser.setUserPassword(PasswordHelper.encodePassword(addUserDTO.getPassword(), salt));
            sysUser.setSalt(salt);
        }
        updateByPrimaryKeySelective(sysUser);

        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setUserId(sysUser.getId());
        sysUserRoleService.delete(sysUserRole);

        sysUserRole.setRoleId(addUserDTO.getRoleID());
        sysUserRoleService.add(sysUserRole);

        return BaseResponseDTO.SUCCESS;
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public BaseResponseDTO deleteUser(Long id) throws Exception {

        deleteByPrimaryKey(id);

        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setUserId(id);
        sysUserRoleService.delete(sysUserRole);


        return BaseResponseDTO.SUCCESS;
    }

    /**
     * 修改密码
     *
     * @param addUserDTO
     * @return
     */
    @Override
    public BaseResponseDTO modifyPassword(AddUserDTO addUserDTO) throws Exception{
        SysUser sysUser = selectByPrimaryKey(addUserDTO.getUserID());

        boolean b = PasswordHelper.checkPassword(sysUser.getUserPassword(), addUserDTO.getOldPassword(), sysUser.getSalt());

        if (!b) return new BaseResponseDTO(AdminResponseEnum.PASSWORD_ERROR);

        String salt = PasswordHelper.generatorRandomNumber();
        sysUser.setUserPassword(PasswordHelper.encodePassword(addUserDTO.getPassword(), salt));
        sysUser.setSalt(salt);
        updateByPrimaryKeySelective(sysUser);
        return BaseResponseDTO.SUCCESS;
    }
}
