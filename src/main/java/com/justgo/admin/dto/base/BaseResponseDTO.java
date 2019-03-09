package com.justgo.admin.dto.base;

import com.justgo.admin.enums.AdminResponseEnum;

public class BaseResponseDTO<T> {

    public static final BaseResponseDTO SUCCESS = new BaseResponseDTO(AdminResponseEnum.SUCCESS);
    public static final BaseResponseDTO FAIL = new BaseResponseDTO(AdminResponseEnum.FAIL);

    /**
     * 响应码
     */
    private String responseCode;
    /**
     * 响应描述
     */
    private String responseDesc;

    /**
     * 返回数据
     */
    private T data;

    /**
     * 扩展字段
     */
    private String extendData;

    private BaseResponseDTO() {
    }


    public BaseResponseDTO(AdminResponseEnum responseEnum) {
        this(responseEnum, null);

    }

    public BaseResponseDTO(T data, AdminResponseEnum responseEnum) {
        this(responseEnum);
        this.data = data;
    }

    public BaseResponseDTO(AdminResponseEnum responseEnum, String extend) {
        if (responseEnum != null) {
            this.responseCode = responseEnum.getResponseCode();
            this.responseDesc = responseEnum.getResponseDesc() + (extend == null ? "" : "(" + extend + ")");
        }
    }

    public BaseResponseDTO(T data, AdminResponseEnum responseEnum, String extend) {
        this.data = data;
        if (responseEnum != null) {
            this.responseCode = responseEnum.getResponseCode();
            this.responseDesc = responseEnum.getResponseDesc() + (extend == null ? "" : "(" + extend + ")");
        }
    }


    /**
     * 获取  响应码
     *
     * @return 响应码
     */
    public String getResponseCode() {
        return this.responseCode;
    }

    /**
     * 设置  响应码
     *
     * @param responseCode 响应码
     */
    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    /**
     * 获取  响应描述
     *
     * @return 响应描述
     */
    public String getResponseDesc() {
        return this.responseDesc;
    }

    /**
     * 设置  响应描述
     *
     * @param responseDesc 响应描述
     */
    public void setResponseDesc(String responseDesc) {
        this.responseDesc = responseDesc;
    }

    /**
     * 获取  返回数据
     *
     * @return 返回数据
     */
    public T getData() {
        return this.data;
    }

    /**
     * 设置  返回数据
     *
     * @param data 返回数据
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * 获取  扩展字段
     *
     * @return 扩展字段
     */
    public String getExtendData() {
        return this.extendData;
    }

    /**
     * 设置  扩展字段
     *
     * @param extendData 扩展字段
     */
    public void setExtendData(String extendData) {
        this.extendData = extendData;
    }
}
