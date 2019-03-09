package com.justgo.admin.dto.common;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Date;

/**
 * 上传图片
 */
public class UploadImgResquestDTO {

    /**
     * 上传图片
     */
    @JSONField(serialize = false)
    private MultipartFile picture;

    /**
     * 目录(在根目录的基础上的拼接目录，比如dir为test，则图片上传的完整路径为:${ImgUploadRootPath}/test)
     */
    private String dir;

    public MultipartFile getPicture() {
        return picture;
    }

    public void setPicture(MultipartFile picture) {
        this.picture = picture;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }
}
