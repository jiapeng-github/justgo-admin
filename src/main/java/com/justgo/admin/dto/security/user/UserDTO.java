package com.justgo.admin.dto.security.user;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 用户DTO
 */
public class UserDTO {

    private Long id;

    /**
     * 角色ID
     */
    private Long roleID;

    /**
     * 用户名称
     */
    private String userName;
    /**
     * 用户状态
     */
    private String userStatus;
    /**
     * 最后登录时间
     */
    private Date lastTime;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 角色名称
     */
    private String roleName;

    private String fullName;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取  用户名称
     *
     * @return 用户名称
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * 设置  用户名称
     *
     * @param userName 用户名称
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取  用户状态
     *
     * @return 用户状态
     */
    public String getUserStatus() {
        return this.userStatus;
    }

    /**
     * 设置  用户状态
     *
     * @param userStatus 用户状态
     */
    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    /**
     * 获取  最后登录时间
     *
     * @return 最后登录时间
     */
    public Date getLastTime() {
        return this.lastTime;
    }

    /**
     * 设置  最后登录时间
     *
     * @param lastTime 最后登录时间
     */
    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    /**
     * 获取  角色名称
     *
     * @return 角色名称
     */
    public String getRoleName() {
        return this.roleName;
    }

    /**
     * 设置  角色名称
     *
     * @param roleName 角色名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * 获取  角色ID
     *
     * @return 角色ID
     */
    public Long getRoleID() {
        return this.roleID;
    }

    /**
     * 设置  角色ID
     *
     * @param roleID 角色ID
     */
    public void setRoleID(Long roleID) {
        this.roleID = roleID;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateTimeString() {
        if (createTime == null){
            return "";
        }else{
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return df.format(createTime);
        }
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

}
