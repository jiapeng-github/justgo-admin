package com.justgo.admin.controller;

import com.justgo.admin.dto.base.BaseResponseDTO;
import com.justgo.admin.dto.common.UploadImgResponseDTO;
import com.justgo.admin.dto.common.UploadImgResquestDTO;
import com.justgo.admin.service.CommonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 管理后台的公共controller
 */
@RequestMapping("/common")
@Controller
public class CommonController {

    private Logger logger = LogManager.getLogger(CommonController.class);

    @Autowired
    private CommonService commonService;

    /**
     * 上传图片
     * @param dto
     * @return
     * @throws Exception
     */
    @RequestMapping("/uploadImg")
    @ResponseBody
    public BaseResponseDTO<UploadImgResponseDTO> uploadImg(UploadImgResquestDTO dto) throws Exception {
        return commonService.uploadImg(dto);
    }
}
