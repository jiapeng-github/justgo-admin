package com.justgo.admin.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.justgo.admin.entity.KnowledgeMore;
import com.justgo.admin.entity.KnowledgePoint;
import org.springframework.web.multipart.MultipartFile;

public class KnowledgeMoreDTO extends KnowledgeMore {

    @JSONField(serialize = false)
    private MultipartFile multipartFile;

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }
}
