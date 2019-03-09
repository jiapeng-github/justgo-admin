package com.justgo.admin.entity;

import javax.persistence.*;

@Table(name = "sys_user_role")
public class SysUserRole {
    @Id
    @Column(name = "USER_ID")
    private Long userId;

    @Id
    @Column(name = "ROLE_ID")
    private Long roleId;

    /**
     * @return USER_ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return ROLE_ID
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * @param roleId
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}