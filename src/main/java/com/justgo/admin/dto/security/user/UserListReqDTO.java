package com.justgo.admin.dto.security.user;

import com.justgo.admin.dto.base.BootstrapTableReqDTO;

/**
 * 用户列表请求
 */
public class UserListReqDTO extends BootstrapTableReqDTO {
    /**
     * 用户名称
     */
    private String userName;

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
