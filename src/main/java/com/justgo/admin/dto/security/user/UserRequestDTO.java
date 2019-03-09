package com.justgo.admin.dto.security.user;

public class UserRequestDTO {

    private Long id;
    /**
     * 用户名称
     */
    private String userName;

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
}
