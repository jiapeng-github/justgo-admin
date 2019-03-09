package com.justgo.admin.enums;

/**
 * author : LYS
 * date : 2018/4/12 上午10:50
 */
public enum AdminResponseEnum {

    NOT_FOUND_404("404", "找不到页面"),

    SUCCESS("0000", "成功"),
    ERROR("9999", "服务器异常"),
    FAIL("9998", "失败"),
    NOT_AUTHORIZATION("9997", "没有权限"),
    REQUEST_ILLEGAL("9996", "非法请求"),
    PARAMETER_IS_NULL("9995", "参数不能为空"),
    DATA_IS_EXIST("9994", "该数据已存在"),
    PARAMETER_VALIDATE_ERROR("9993", "参数检查错误"),
    PARAMETER_VALIDATE_FAIL("9992", "参数格式不正确"),

    /***********权限*************/
    ROLE_NOT_EXIST("1001", "角色不存在"),
    USER_EXIST("1002", "用户名已存在"),
    NO_LOGIN("1003","没有登录"),
    PASSWORD_ERROR("1004","密码错误!!"),
    
    /*********** app版本 ************/

    VERSION_MUST_GREATER_THAN_CURRENT("2001", "版本必须大于当前版本"),
    GET_CURRENT_VERSION_FAIL("2002", "获取当前版本失败"),
    VERSION_CAN_NOT_SAME("2003", "新增版本不能和当前版本相同"),

    ;
    /**
     * 返回代码
     */
    public String responseCode;
    /**
     * 返回描述
     */
    public String responseDesc;

    AdminResponseEnum(String responseCode, String responseDesc) {
        this.responseCode = responseCode;
        this.responseDesc = responseDesc;
    }


    /**
     * 获取  返回代码
     *
     * @return 返回代码
     */
    public String getResponseCode() {
        return this.responseCode;
    }

    /**
     * 设置  返回代码
     *
     * @param responseCode 返回代码
     */
    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    /**
     * 获取  返回描述
     *
     * @return 返回描述
     */
    public String getResponseDesc() {
        return this.responseDesc;
    }

    /**
     * 设置  返回描述
     *
     * @param responseDesc 返回描述
     */
    public void setResponseDesc(String responseDesc) {
        this.responseDesc = responseDesc;
    }
}
