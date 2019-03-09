package com.justgo.admin.dto.base;

/**
 * BootstrapTable 请求的基础数据
 */
public class BootstrapTableReqDTO {

    /**
     * 请求页码
     */
    private Integer pageNumber;
    /**
     * 请求分页大小
     */
    private Integer pageSize;
    private String sortName;
    /**
     * 顺序
     */
    private String order;

    /**
     * 获取  请求页码
     *
     * @return 请求页码
     */
    public Integer getPageNumber() {
        return this.pageNumber;
    }

    /**
     * 设置  请求页码
     *
     * @param pageNumber 请求页码
     */
    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    /**
     * 获取  请求分页大小
     *
     * @return 请求分页大小
     */
    public Integer getPageSize() {
        return this.pageSize;
    }

    /**
     * 设置  请求分页大小
     *
     * @param pageSize 请求分页大小
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getSortName() {
        return this.sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    /**
     * 获取  顺序
     *
     * @return 顺序
     */
    public String getOrder() {
        return this.order;
    }

    /**
     * 设置  顺序
     *
     * @param order 顺序
     */
    public void setOrder(String order) {
        this.order = order;
    }
}

