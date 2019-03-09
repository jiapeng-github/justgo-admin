package com.justgo.admin.service.impl;

import com.justgo.admin.config.APIConfig;
import com.justgo.admin.dto.base.BaseResponseDTO;
import com.justgo.admin.dto.common.UploadImgResponseDTO;
import com.justgo.admin.dto.common.UploadImgResquestDTO;
import com.justgo.admin.service.CommonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

import static com.justgo.admin.enums.AdminResponseEnum.PARAMETER_IS_NULL;
import static com.justgo.admin.enums.AdminResponseEnum.SUCCESS;

/**
 * Created by fancz on 2018/4/13.
 */
@Component
public class CommonServiceImpl implements CommonService {

    private Logger logger = LogManager.getLogger(CommonServiceImpl.class);

    @Override
    public BaseResponseDTO<UploadImgResponseDTO> uploadImg(UploadImgResquestDTO dto) throws Exception {
        byte[] bytes = dto.getPicture().getBytes();
        if (bytes.length == 0)
            return new BaseResponseDTO(PARAMETER_IS_NULL, "picture");
        MultipartFile picture = dto.getPicture();
        String originalFilename = picture.getOriginalFilename();
        String newFilename = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
        if (dto.getDir().startsWith("/"))dto.setDir(dto.getDir().substring(1));//前面不需要"/"，有则去掉
        if (!dto.getDir().endsWith("/"))dto.setDir(dto.getDir()+"/");//后面需要"/"，没有则补上
        String imgPath = dto.getDir() + newFilename;
        //检查并创建目录
        String dir = APIConfig.getImgUploadRootPath() + dto.getDir();
        File file = new File(dir);
        if(!file.exists()) {
            file.mkdirs();
        }
        picture.transferTo(new File(APIConfig.getImgUploadRootPath() + imgPath));
        UploadImgResponseDTO responseDTO = new UploadImgResponseDTO();
        responseDTO.setImgPath(imgPath);
        responseDTO.setImgUrl(APIConfig.getImageServerURL() + imgPath);
        logger.info("CommonService||上传图片||imgPath:{},全路径:{}",imgPath,APIConfig.getImageServerURL() + imgPath);
        return new BaseResponseDTO(responseDTO,SUCCESS);
    }
}
