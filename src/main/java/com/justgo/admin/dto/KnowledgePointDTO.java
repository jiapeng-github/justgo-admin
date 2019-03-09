package com.justgo.admin.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.justgo.admin.entity.KnowledgePoint;
import org.springframework.web.multipart.MultipartFile;

public class KnowledgePointDTO extends KnowledgePoint {

    @JSONField(serialize = false)
    private MultipartFile multipartFile;

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }
}
