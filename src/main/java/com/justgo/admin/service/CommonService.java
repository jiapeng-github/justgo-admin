package com.justgo.admin.service;

import com.justgo.admin.dto.base.BaseResponseDTO;
import com.justgo.admin.dto.common.UploadImgResponseDTO;
import com.justgo.admin.dto.common.UploadImgResquestDTO;

/**
 * Created by fancz on 2018/4/13.
 */
public interface CommonService {

    /**
     * 上传图片
     * @param dto
     * @return
     * @throws Exception
     */
    BaseResponseDTO<UploadImgResponseDTO> uploadImg(UploadImgResquestDTO dto) throws Exception;

}
