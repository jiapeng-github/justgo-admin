package com.justgo.admin.entity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "sys_user")
public class SysUser {
    /**
     * ID
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 登录名称
     */
    @Column(name = "USER_NAME")
    private String userName;

    /**
     * 登录密码
     */
    @Column(name = "USER_PASSWORD")
    private String userPassword;

    /**
     * 盐
     */
    @Column(name = "SALT")
    private String salt;

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
    @Column(name = "USER_STATUS")
    private Integer userStatus;

    /**
     * 最后登录时间
     */
    @Column(name = "LAST_TIME")
    private Date lastTime;

    /**
     * 创建时间
     */
    @Column(name = "CREATE_TIME")
    private Date createTime;

    @Column(name = "FULL_NAME")
    private String fullName;

    /**
     * 获取ID
     *
     * @return ID - ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置ID
     *
     * @param id ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取登录名称
     *
     * @return USER_NAME - 登录名称
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置登录名称
     *
     * @param userName 登录名称
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取登录密码
     *
     * @return USER_PASSWORD - 登录密码
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * 设置登录密码
     *
     * @param userPassword 登录密码
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * 获取盐
     *
     * @return SALT - 盐
     */
    public String getSalt() {
        return salt;
    }

    /**
     * 设置盐
     *
     * @param salt 盐
     */
    public void setSalt(String salt) {
        this.salt = salt;
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
     * @return USER_STATUS - 状态
     * 1:正常
     * 2:禁用
     */
    public Integer getUserStatus() {
        return userStatus;
    }

    /**
     * 设置状态
     * 1:正常
     * 2:禁用
     *
     * @param userStatus 状态
     *                   1:正常
     *                   2:禁用
     */
    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    /**
     * 获取最后登录时间
     *
     * @return LAST_TIME - 最后登录时间
     */
    public Date getLastTime() {
        return lastTime;
    }

    /**
     * 设置最后登录时间
     *
     * @param lastTime 最后登录时间
     */
    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    /**
     * 获取创建时间
     *
     * @return CREATE_TIME - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}