package com.justgo.admin.entity;

import javax.persistence.*;

@Table(name = "sys_resource")
public class SysResource {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 资源名称
     */
    @Column(name = "RESOURCE_NAME")
    private String resourceName;

    /**
     * 资源URL
     */
    @Column(name = "RESOURCE_URL")
    private String resourceUrl;

    @Column(name = "F_ID")
    private Long fId;

    /**
     * 权限字符串
     */
    @Column(name = "PERMISSION")
    private String permission;

    /**
     * 资源顺序
     */
    @Column(name = "PRIORITY")
    private Integer priority;

    /**
     * 资源类型
     * 1:菜单标题
     * 2:菜单
     * 3:功能
     */
    @Column(name = "RESOURCE_TYPE")
    private Integer resourceType;

    /**
     * 图标
     */
    @Column(name = "ICON")
    private String icon;

    /**
     * 状态
     * 1:正常
     * 2:禁用
     */
    @Column(name = "RESOURCE_STATUS")
    private Integer resourceStatus;

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
     * 获取资源名称
     *
     * @return RESOURCE_NAME - 资源名称
     */
    public String getResourceName() {
        return resourceName;
    }

    /**
     * 设置资源名称
     *
     * @param resourceName 资源名称
     */
    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    /**
     * 获取资源URL
     *
     * @return RESOURCE_URL - 资源URL
     */
    public String getResourceUrl() {
        return resourceUrl;
    }

    /**
     * 设置资源URL
     *
     * @param resourceUrl 资源URL
     */
    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }


    /**
     * 获取权限字符串
     *
     * @return PERMISSION - 权限字符串
     */
    public String getPermission() {
        return permission;
    }

    /**
     * 设置权限字符串
     *
     * @param permission 权限字符串
     */
    public void setPermission(String permission) {
        this.permission = permission;
    }

    /**
     * 获取资源顺序
     *
     * @return PRIORITY - 资源顺序
     */
    public Integer getPriority() {
        return priority;
    }

    /**
     * 设置资源顺序
     *
     * @param priority 资源顺序
     */
    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    /**
     * 获取资源类型
     * 1:菜单标题
     * 2:菜单
     * 3:功能
     *
     * @return RESOURCE_TYPE - 资源类型
     * 1:菜单标题
     * 2:菜单
     * 3:功能
     */
    public Integer getResourceType() {
        return resourceType;
    }

    /**
     * 设置资源类型
     * 1:菜单标题
     * 2:菜单
     * 3:功能
     *
     * @param resourceType 资源类型
     *                     1:菜单标题
     *                     2:菜单
     *                     3:功能
     */
    public void setResourceType(Integer resourceType) {
        this.resourceType = resourceType;
    }

    /**
     * 获取图标
     *
     * @return ICON - 图标
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置图标
     *
     * @param icon 图标
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 获取状态
     * 1:正常
     * 2:禁用
     *
     * @return RESOURCE_STATUS - 状态
     * 1:正常
     * 2:禁用
     */
    public Integer getResourceStatus() {
        return resourceStatus;
    }

    /**
     * 设置状态
     * 1:正常
     * 2:禁用
     *
     * @param resourceStatus 状态
     *                       1:正常
     *                       2:禁用
     */
    public void setResourceStatus(Integer resourceStatus) {
        this.resourceStatus = resourceStatus;
    }

    public Long getFId() {
        return this.fId;
    }

    public void setFId(Long fId) {
        this.fId = fId;
    }
}