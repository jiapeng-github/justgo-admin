package com.justgo.admin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.justgo.admin.dto.base.BaseResponseDTO;
import com.justgo.admin.dto.base.BootstrapTableResDTO;
import com.justgo.admin.entity.KeyPoint;
import com.justgo.admin.entity.KeyPoint;
import com.justgo.admin.entity.SysUser;
import com.justgo.admin.service.KeyPointService;
import com.justgo.admin.service.KeyPointService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

/**
 * 重难点
 *
 * @auth jp
 * @Date 2018/9/20
 */
@Controller
@RequestMapping("/keyPoint")
public class KeyController {
    private Logger logger = LogManager.getLogger(KeyController.class);

    @Autowired
    private KeyPointService keyPointService;

    @RequestMapping("/index")
    @RequiresPermissions("keyPoint:view")
    public String index() {
        return "keyPoint/index";
    }

    /**
     * 知识点列表
     *
     * @return
     */
    @RequestMapping("/list")
    @RequiresPermissions("keyPoint:list")
    @ResponseBody
    public BootstrapTableResDTO<KeyPoint> list(KeyPoint entity, Integer pageNumber, Integer pageSize) throws Exception{
        PageHelper.startPage(pageNumber == null ? 1 : pageNumber, pageSize == null ? 100 : pageSize);
        List<KeyPoint> keyPoints = keyPointService.selectKeyPoint(entity);
        PageInfo<KeyPoint> pages = new PageInfo<>(keyPoints);
        long total = pages.getTotal();
        BootstrapTableResDTO<KeyPoint> respDto = new BootstrapTableResDTO<>();
        respDto.setTotal(total);
        respDto.setRows(keyPoints);
        return respDto;
    }

    /**
     * 知识点列表
     *
     * @return
     */
    @RequestMapping("/detail")
    @ResponseBody
    @RequiresPermissions("keyPoint:detail")
    public KeyPoint detail(KeyPoint keyPoint) throws Exception{
        return (KeyPoint) keyPointService.selectOne(keyPoint);
    }

    /**
     * 创建知识点
     * @param keyPoint
     * @return
     */
    @RequestMapping("/create")
    @ResponseBody
    @RequiresPermissions("keyPoint:create")
    public BaseResponseDTO create(KeyPoint keyPoint, MultipartFile multipartFile) throws Exception{
        SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();

        keyPoint.setCreateTime(new Date());
        keyPoint.setCreateUser(sysUser.getFullName());
        if (multipartFile != null){
            byte[] file = multipartFile.getBytes();
            String originalFilename = multipartFile.getOriginalFilename();
            keyPoint.setFileName(originalFilename);
            keyPoint.setFile(file);
        }
        keyPointService.add(keyPoint);
        return BaseResponseDTO.SUCCESS;
    }

    /**
     * 更新知识点
     * @param keyPoint
     * @return
     */
    @RequestMapping("/modify")
    @ResponseBody
    @RequiresPermissions("keyPoint:modify")
    public BaseResponseDTO modify(KeyPoint keyPoint, MultipartFile multipartFile) throws Exception{
        if (multipartFile != null){
            byte[] file = multipartFile.getBytes();
            String originalFilename = multipartFile.getOriginalFilename();
            keyPoint.setFileName(originalFilename);
            keyPoint.setFile(file);
        }
        keyPointService.updateByPrimaryKeySelective(keyPoint);
        return BaseResponseDTO.SUCCESS;
    }

    /**
     * 删除
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    @RequiresPermissions("keyPoint:delete")
    public BaseResponseDTO delete(String ids) throws Exception{
        keyPointService.deleteByIds(ids);
        return BaseResponseDTO.SUCCESS;
    }

    @RequestMapping("/downloadFile/{id}")
    public void downloadFile(@PathVariable("id") Integer id, final HttpServletResponse response) throws Exception{
        KeyPoint keyPoint = new KeyPoint();
        keyPoint.setId(id);
        keyPoint = keyPointService.selectOne(keyPoint);
        byte[] file = keyPoint.getFile();
        OutputStream outputSream = response.getOutputStream();
        outputSream.write(file);
        outputSream.flush();
    }

}
