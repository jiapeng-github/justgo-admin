package com.justgo.admin.shrio;

import com.justgo.admin.dto.base.UserInfo;
import com.justgo.admin.entity.SysResource;
import com.justgo.admin.entity.SysRole;
import com.justgo.admin.entity.SysUser;
import com.justgo.admin.entity.SysUserRole;
import com.justgo.admin.service.SysResourceService;
import com.justgo.admin.service.SysRoleService;
import com.justgo.admin.service.SysUserRoleService;
import com.justgo.admin.service.SysUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * author : WUJINGZHI
 * date: 2017/8/28 10:20
 */
@Component
public class MyRealm extends AuthorizingRealm {

    private Logger logger = LogManager.getLogger(MyRealm.class);

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysResourceService sysResourceService;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysRoleService sysRoleService;

    @Resource(name = "adminCredentialsMatcher")
    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        super.setCredentialsMatcher(credentialsMatcher);
    }

    /**
     * 验证当前登录的用户
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取登录账号
        String loginName = (String) token.getPrincipal();
        SysUser sysUser = new SysUser();
        sysUser.setUserName(loginName);

        try {
            sysUser = sysUserService.selectOne(sysUser);
            // 用户不存在
            if (sysUser == null) {
                logger.error("登录失败,用户不存在!!");
                throw new AuthenticationException();
            }

            String salt = sysUser.getSalt();

            return new SimpleAuthenticationInfo(sysUser, sysUser.getUserPassword(),
                                                ByteSource.Util.bytes(salt), getName());
        } catch (Exception e) {
            logger.error("登录失败!!");
            throw new AuthenticationException();
        }
    }

    /**
     * 为当限前登录的用户授予角色和权限
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        SysUser user = (SysUser) principals.getPrimaryPrincipal();
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        //如果是admin登录获取全部权限,无论是不是新增的资源
        List<SysResource> sysResources;
        if ("admin".equals(user.getUserName())) {
            authorizationInfo.addRole("admin");
            Example example = new Example(SysResource.class);
            example.createCriteria().andEqualTo("resourceStatus", 1);
            //时间倒序
            example.orderBy("priority").asc();
            sysResources = sysResourceService.selectByExample(example);
            authorizationInfo.addStringPermission("*:*");
            session.setAttribute("_sysResources", sysResources);
            session.setAttribute("_sysUser", user);
            UserInfo userInfo = new UserInfo();
            userInfo.setUserID(1L);
            userInfo.setFullName("系统管理员");
            userInfo.setRoleName("超级管理员");
            userInfo.setUserName("admin");
            userInfo.setAppID("2");
            session.setAttribute("_userInfo", userInfo);
            return authorizationInfo;
        }

        //普通角色数据库获取权限
        sysResources = sysResourceService.selectByUserID(user.getId());
        Set<String> stringPermissions = new HashSet<>();
        sysResources.stream()
                    .filter(sysResource -> sysResource.getPermission() != null && !"".equals(sysResource.getPermission()))
                    .forEach(sysResource ->
                                     stringPermissions.add(sysResource.getPermission())
                    );
        //菜单权限--登录用户都拥有无需授权
        stringPermissions.add("menu:view");
        stringPermissions.add("index:view");
        stringPermissions.add("user:info");
        authorizationInfo.setStringPermissions(stringPermissions);


        session.setAttribute("_sysResources", sysResources);
        session.setAttribute("_sysUser", user);

        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setUserId(user.getId());
        List<SysUserRole> sysUserRoles = sysUserRoleService.select(sysUserRole);

        UserInfo userInfo = new UserInfo();
        user = sysUserService.selectOne(user);
        userInfo.setUserName(user.getUserName());
        userInfo.setFullName(user.getFullName());
        if (!sysUserRoles.isEmpty()) {
            SysUserRole _sysUserRole = sysUserRoles.get(0);
            SysRole sysRole = sysRoleService.selectByPrimaryKey(_sysUserRole.getRoleId());
            userInfo.setRoleName(sysRole.getRoleName());
            userInfo.setAppID(sysRole.getAppID() + "");
            userInfo.setUserID(user.getId());
        }
        session.setAttribute("_userInfo", userInfo);


        return authorizationInfo;
    }


    private void updatePermission() {

    }

}
