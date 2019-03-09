package com.justgo.admin.dto.base;

import java.io.Serializable;

public class BaseQuery  implements Serializable {
    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 截止时间
     */
    private String endTime;
    /**
     * 页数
     */
    private String pageNumber="1";
    /**
     * 每页数据条数
     */
    private String pageSize="10";

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }
}
