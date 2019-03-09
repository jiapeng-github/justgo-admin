package com.justgo.admin.dto.base;

public class UserInfo {

    private String userName;
    private String roleName;
    private String appID;
    private Long userID;
    private String fullName;

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getAppID() {
        return this.appID;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }

    public Long getUserID() {
        return this.userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
