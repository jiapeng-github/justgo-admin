package com.justgo.admin.entity;

import javax.persistence.*;

@Table(name = "sys_role_resource")
public class SysRoleResource {
    @Id
    @Column(name = "ROLE_ID")
    private Long roleId;

    @Id
    @Column(name = "RESOURCE_ID")
    private Long resourceId;

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

    /**
     * @return RESOURCE_ID
     */
    public Long getResourceId() {
        return resourceId;
    }

    /**
     * @param resourceId
     */
    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }
}