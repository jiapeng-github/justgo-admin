package com.justgo.admin.controller;

import com.justgo.admin.dto.base.BaseResponseDTO;
import com.justgo.admin.dto.base.BootstrapTableResDTO;
import com.justgo.admin.dto.base.BootstrapTreeDTO;
import com.justgo.admin.dto.base.UserInfo;
import com.justgo.admin.dto.security.resources.ResourcesListReqDTO;
import com.justgo.admin.dto.security.role.RoleDTO;
import com.justgo.admin.dto.security.role.RoleListReqDTO;
import com.justgo.admin.dto.security.user.AddUserDTO;
import com.justgo.admin.dto.security.user.UserDTO;
import com.justgo.admin.dto.security.user.UserListReqDTO;
import com.justgo.admin.entity.SysResource;
import com.justgo.admin.entity.SysRole;
import com.justgo.admin.enums.AdminResponseEnum;
import com.justgo.admin.service.SysResourceService;
import com.justgo.admin.service.SysRoleService;
import com.justgo.admin.service.SysUserService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("security")
public class SecurityController {

    private Logger logger = LogManager.getLogger(SecurityController.class);
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysResourceService sysResourceService;
    @Autowired
    private RequestMappingHandlerMapping handlerMapping;

    /**
     * 没有权限
     */
    @RequestMapping("unauthorized")
    public String unauthorized(HttpServletRequest request) {

        String asynch = request.getHeader("X-Requested-With");
        String re = request.getHeader("referer");
        if (asynch == null) {
            return "security/authority/authorization";
        }
        return "security/authority/authorization-json";
    }

    /**
     * 打开用户管理视图
     *
     * @return
     */
    @RequestMapping("userView")
    public String userView() {
        return "security/user/user";
    }


    /**
     * 用户数据
     *
     * @return
     */
    @RequestMapping("userList")
    @ResponseBody
    public BootstrapTableResDTO<UserDTO> userList(@RequestBody(required = false) UserListReqDTO dto) {
        return sysUserService.userList(dto);
    }

    /**
     * 获取全部角色
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("getAllRole")
    public List<SysRole> getAllRole() {
        return sysRoleService.selectAll();
    }

    /**
     * 增加用户
     *
     * @param addUserDTO
     * @return
     */
    @ResponseBody
    @RequestMapping("addUser")
    public BaseResponseDTO addUser(@RequestBody AddUserDTO addUserDTO) throws Exception {
        return sysUserService.addUser(addUserDTO);
    }

    /**
     * 编辑用户
     *
     * @param addUserDTO
     * @return
     */
    @ResponseBody
    @RequestMapping("editUser")
    public BaseResponseDTO editUser(@RequestBody AddUserDTO addUserDTO) throws Exception {
        return sysUserService.editUser(addUserDTO);
    }

    /**
     * 删除用户
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("deleteUser")
    public BaseResponseDTO deleteUser(Long id) throws Exception {
        return sysUserService.deleteUser(id);
    }

    /**
     * 打开角色管理视图
     *
     * @return
     */
    @RequestMapping("roleView")
    public String roleView() {
        return "security/role/role";
    }

    /**
     * 角色数据
     *
     * @return
     */
    @RequestMapping("roleList")
    @ResponseBody
    public BootstrapTableResDTO<RoleDTO> roleList(RoleListReqDTO roleListReqDTO) {
        return sysRoleService.roleList(roleListReqDTO);
    }

    /**
     * 增加角色
     *
     * @param sysRole
     * @return
     */
    @ResponseBody
    @RequestMapping("addRole")
    public BaseResponseDTO addUser(@RequestBody SysRole sysRole) throws Exception {
        return sysRoleService.addRole(sysRole);
    }

    /**
     * 编辑角色
     *
     * @param sysRole
     * @return
     */
    @ResponseBody
    @RequestMapping("editRole")
    public BaseResponseDTO editRole(@RequestBody SysRole sysRole) throws Exception {
        return sysRoleService.editRole(sysRole);
    }

    /**
     * 删除角色
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("deleteRole")
    public BaseResponseDTO deleteRole(Long id) throws Exception {
        return sysRoleService.deleteRole(id);
    }

    /**
     * 获取根据roleID获取全部资源(包含未分配的资源)
     *
     * @param roleID
     * @return
     */
    @ResponseBody
    @RequestMapping("getALLResource/{roleID}")
    public List<BootstrapTreeDTO> getALLResource(@PathVariable("roleID") Long roleID) {
        return sysResourceService.getALLResource(roleID);
    }

    /**
     * 授权
     */
    @ResponseBody
    @RequestMapping("authorization")
    public BaseResponseDTO authorization(Long roleID, Long[] resourcesID) {
        return sysResourceService.authorization(roleID, resourcesID);
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("userInfo")
    public BaseResponseDTO<UserInfo> userInfo() {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute("_userInfo");
        if (userInfo == null) {
            userInfo = new UserInfo();
            userInfo.setUserName("未知");
        }

        return new BaseResponseDTO<>(userInfo, AdminResponseEnum.SUCCESS);
    }

    /**
     * 资源
     *
     * @return
     */
    @RequestMapping("resourcesView")
    public String resourcesView() {

        return "security/resources/resources";
    }

    /**
     * 获取所有的映射资源
     *
     * @return
     */
    @RequestMapping("getResourcesUrls")
    @ResponseBody
    public BaseResponseDTO<Set<String>> getResourcesUrls() {
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = handlerMapping.getHandlerMethods();
        Set<String> pathURL = new HashSet<>();
        Set<String> responseURLs = new HashSet<>();
        handlerMethods.entrySet()
                      .stream()
                      .map(Map.Entry::getKey)
                      .map(RequestMappingInfo::getPatternsCondition)
                      .map(PatternsRequestCondition::getPatterns)
                      .forEach(pathURL::addAll);
        pathURL.forEach(s -> responseURLs.add(s.replaceAll("\\{.*?\\}", "**")));
        return new BaseResponseDTO<>(responseURLs, AdminResponseEnum.SUCCESS);
    }

    /**
     * 根据类型获取父资源
     * @return
     */
    @RequestMapping("getResourceFidByType/{resourcesType}")
    @ResponseBody
    public BaseResponseDTO<List<SysResource>> getResourceFidByType(@PathVariable("resourcesType") Integer resourcesType){
        return sysResourceService.getResourceFidByType(resourcesType);
    }

    /**
     * 增加资源
     * @param sysResource
     * @return
     */
    @RequestMapping("addResource")
    @ResponseBody
    public BaseResponseDTO addResource(@RequestBody SysResource sysResource){
        return sysResourceService.addResource(sysResource);
    }

    /**
     * 获取资源
     * @param reqDTO
     * @return
     */
    @RequestMapping("getResources")
    @ResponseBody
    public BootstrapTableResDTO<SysResource> getResources(@RequestBody(required = false) ResourcesListReqDTO reqDTO){
        return sysResourceService.getResources(reqDTO);
    }

    /**
     * 更新资源
     * @param sysResource
     * @return
     */
    @RequestMapping("updateResource")
    @ResponseBody
    public BaseResponseDTO updateResource(@RequestBody SysResource sysResource){
        return sysResourceService.updateResource(sysResource);
    }

    /**
     * 修改密码
     * @param addUserDTO
     * @return
     * @throws Exception
     */
    @RequestMapping("modifyPassword")
    @ResponseBody
    public BaseResponseDTO modifyPassword(@RequestBody AddUserDTO addUserDTO) throws Exception{
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute("_userInfo");
        addUserDTO.setUserID(userInfo.getUserID());
        return sysUserService.modifyPassword(addUserDTO);
    }


}
