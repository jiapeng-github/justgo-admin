package com.justgo.admin.entity;

import javax.persistence.*;

@Table(name = "sys_role")
public class SysRole {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 名称
     */
    @Column(name = "ROLE_NAME")
    private String roleName;

    /**
     * 描述
     */
    @Column(name = "DESCRIPTION")
    private String description;

    /**
     * F_ID
     */
    @Column(name = "F_ID")
    private Long fId;

    /**
     * 状态
     * 1:正常
     * 2:禁用
     */
    @Column(name = "ROLE_STATUS")
    private Integer roleStatus;

    /**
     * appID
     */
    @Column(name = "APP_ID")
    private Integer appID;

    /**
     * @return ID
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取名称
     *
     * @return ROLE_NAME - 名称
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 设置名称
     *
     * @param roleName 名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * 获取描述
     *
     * @return DESCRIPTION - 描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置描述
     *
     * @param description 描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取F_ID
     *
     * @return F_ID - F_ID
     */
    public Long getfId() {
        return fId;
    }

    /**
     * 设置F_ID
     *
     * @param fId F_ID
     */
    public void setfId(Long fId) {
        this.fId = fId;
    }

    /**
     * 获取状态
     * 1:正常
     * 2:禁用
     *
     * @return ROLE_STATUS - 状态
     * 1:正常
     * 2:禁用
     */
    public Integer getRoleStatus() {
        return roleStatus;
    }

    /**
     * 设置状态
     * 1:正常
     * 2:禁用
     *
     * @param roleStatus 状态
     *                   1:正常
     *                   2:禁用
     */
    public void setRoleStatus(Integer roleStatus) {
        this.roleStatus = roleStatus;
    }


    /**
     * 获取  F_ID
     *
     * @return F_ID
     */
    public Long getFId() {
        return this.fId;
    }

    /**
     * 设置  F_ID
     *
     * @param fId F_ID
     */
    public void setFId(Long fId) {
        this.fId = fId;
    }

    /**
     * 获取  appID
     *
     * @return appID
     */
    public Integer getAppID() {
        return this.appID;
    }

    /**
     * 设置  appID
     *
     * @param appID appID
     */
    public void setAppID(Integer appID) {
        this.appID = appID;
    }
}