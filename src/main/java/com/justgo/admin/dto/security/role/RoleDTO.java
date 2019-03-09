package com.justgo.admin.dto.security.role;

public class RoleDTO {


    private Long id;

    /**
     * 名称
     */
    private String roleName;

    /**
     * 描述
     */
    private String description;

    /**
     * F_ID
     */
    private Long fId;

    /**
     * 父名称
     */
    private String fName;

    /**
     * 状态
     * 1:正常
     * 2:禁用
     */
    private Integer roleStatus;

    private Integer appID;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取  名称
     *
     * @return 名称
     */
    public String getRoleName() {
        return this.roleName;
    }

    /**
     * 设置  名称
     *
     * @param roleName 名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * 获取  描述
     *
     * @return 描述
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * 设置  描述
     *
     * @param description 描述
     */
    public void setDescription(String description) {
        this.description = description;
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
     * 获取  父名称
     *
     * @return 父名称
     */
    public String getFName() {
        return this.fName;
    }

    /**
     * 设置  父名称
     *
     * @param fName 父名称
     */
    public void setFName(String fName) {
        this.fName = fName;
    }

    /**
     * 获取  状态
     * 1:正常
     * 2:禁用
     *
     * @return 状态
     * 1:正常
     * 2:禁用
     */
    public Integer getRoleStatus() {
        return this.roleStatus;
    }

    /**
     * 设置  状态
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

    public Integer getAppID() {
        return this.appID;
    }

    public void setAppID(Integer appID) {
        this.appID = appID;
    }
}
