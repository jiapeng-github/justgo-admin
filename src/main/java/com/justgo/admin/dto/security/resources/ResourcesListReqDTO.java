package com.justgo.admin.dto.security.resources;

import com.justgo.admin.dto.base.BootstrapTableReqDTO;

public class ResourcesListReqDTO extends BootstrapTableReqDTO {
    private Long id;

    private Long fId;
    /**
     * 是否是查看详情
     */
    private Integer isShowDetail;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getFId() {
        return this.fId;
    }

    public void setFId(Long fId) {
        this.fId = fId;
    }

    /**
     * 获取  是否是查看详情
     *
     * @return 是否是查看详情
     */
    public Integer getIsShowDetail() {
        return this.isShowDetail;
    }

    /**
     * 设置  是否是查看详情
     *
     * @param isShowDetail 是否是查看详情
     */
    public void setIsShowDetail(Integer isShowDetail) {
        this.isShowDetail = isShowDetail;
    }
}
