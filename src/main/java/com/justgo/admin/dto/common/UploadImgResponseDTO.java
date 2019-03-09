package com.justgo.admin.dto.common;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.web.multipart.MultipartFile;

/**
 * 上传图片
 */
public class UploadImgResponseDTO {

    /**
     * 目录(在根目录的基础上的拼接目录，比如dir为test，则图片上传的完整路径为:${ImgUploadRootPath}/test)
     */
    private String imgPath;
    /**
     * 可访问url
     */
    private String imgUrl;

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
