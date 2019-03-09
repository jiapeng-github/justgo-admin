package com.justgo.admin.dto.security.user;

public class AddUserDTO {

    /**
     * 用户ID
     */
    private Long userID;
    /**
     * 角色ID
     */
    private Long roleID;
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 确认密码
     */
    private String confirmPassword;

    /**
     * 旧密码
     */
    private String oldPassword;

    private String fullName;

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
     * 获取  密码
     *
     * @return 密码
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * 设置  密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取  确认密码
     *
     * @return 确认密码
     */
    public String getConfirmPassword() {
        return this.confirmPassword;
    }

    /**
     * 设置  确认密码
     *
     * @param confirmPassword 确认密码
     */
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    /**
     * 获取  用户ID
     *
     * @return 用户ID
     */
    public Long getUserID() {
        return this.userID;
    }

    /**
     * 设置  用户ID
     *
     * @param userID 用户ID
     */
    public void setUserID(Long userID) {
        this.userID = userID;
    }

    /**
     * 获取  旧密码
     *
     * @return 旧密码
     */
    public String getOldPassword() {
        return this.oldPassword;
    }

    /**
     * 设置  旧密码
     *
     * @param oldPassword 旧密码
     */
    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
