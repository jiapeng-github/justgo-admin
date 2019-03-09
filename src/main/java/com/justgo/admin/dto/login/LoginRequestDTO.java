package com.justgo.admin.dto.login;

public class LoginRequestDTO {
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 验证码
     */
    private String checkCode;


    /**
     * 获取  用户名
     *
     * @return 用户名
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * 设置  用户名
     *
     * @param userName 用户名
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
     * 获取  验证码
     *
     * @return 验证码
     */
    public String getCheckCode() {
        return this.checkCode;
    }

    /**
     * 设置  验证码
     *
     * @param checkCode 验证码
     */
    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }
}
