package com.justgo.admin.controller;

import com.alibaba.fastjson.annotation.JSONField;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.justgo.admin.dto.KnowledgePointDTO;
import com.justgo.admin.dto.base.BaseResponseDTO;
import com.justgo.admin.dto.base.BootstrapTableResDTO;
import com.justgo.admin.entity.KnowledgePoint;
import com.justgo.admin.entity.SysUser;
import com.justgo.admin.service.KnowledgePointService;
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
 * 知识点
 *
 * @auth jp
 * @Date 2018/9/20
 */
@Controller
@RequestMapping("/knowledgePoint")
public class KnowledgePointController {
    private Logger logger = LogManager.getLogger(KnowledgePointController.class);

    @Autowired
    private KnowledgePointService knowledgePointService;

    @RequestMapping("/index")
    @RequiresPermissions("knowledgePoint:view")
    public String index() {
        return "knowledgePoint/index";
    }

    /**
     * 知识点列表
     *
     * @return
     */
    @RequestMapping("/list")
    @RequiresPermissions("knowledgePoint:list")
    @ResponseBody
    public BootstrapTableResDTO<KnowledgePoint> list(KnowledgePoint entity, Integer pageNumber, Integer pageSize) throws Exception{
        PageHelper.startPage(pageNumber == null ? 1 : pageNumber, pageSize == null ? 100 : pageSize);
        List<KnowledgePoint> knowledgePoints = knowledgePointService.selectKnowledgePoint(entity);
        PageInfo<KnowledgePoint> pages = new PageInfo<>(knowledgePoints);
        long total = pages.getTotal();
        BootstrapTableResDTO<KnowledgePoint> respDto = new BootstrapTableResDTO<>();
        respDto.setTotal(total);
        respDto.setRows(knowledgePoints);
        return respDto;
    }

    /**
     * 知识点列表
     *
     * @return
     */
    @RequestMapping("/detail")
    @ResponseBody
    @RequiresPermissions("knowledgePoint:detail")
    public KnowledgePoint detail(KnowledgePoint knowledgePoint) throws Exception{
        return (KnowledgePoint) knowledgePointService.selectOne(knowledgePoint);
    }

    /**
     * 创建知识点
     * @param knowledgePoint
     * @return
     */
    @RequestMapping("/create")
    @ResponseBody
    @RequiresPermissions("knowledgePoint:create")
    public BaseResponseDTO create(KnowledgePoint knowledgePoint, MultipartFile multipartFile) throws Exception{
        SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();

        knowledgePoint.setCreateTime(new Date());
        knowledgePoint.setCreateUser(sysUser.getFullName());
        if (multipartFile != null){
            byte[] file = multipartFile.getBytes();
            String originalFilename = multipartFile.getOriginalFilename();
            knowledgePoint.setFileName(originalFilename);
            knowledgePoint.setFile(file);
        }
        knowledgePointService.add(knowledgePoint);
        return BaseResponseDTO.SUCCESS;
    }

    /**
     * 更新知识点
     * @param knowledgePoint
     * @return
     */
    @RequestMapping("/modify")
    @ResponseBody
    @RequiresPermissions("knowledgePoint:modify")
    public BaseResponseDTO modify(KnowledgePoint knowledgePoint, MultipartFile multipartFile) throws Exception{
        if (multipartFile != null){
            byte[] file = multipartFile.getBytes();
            String originalFilename = multipartFile.getOriginalFilename();
            knowledgePoint.setFileName(originalFilename);
            knowledgePoint.setFile(file);
        }
        knowledgePointService.updateByPrimaryKeySelective(knowledgePoint);
        return BaseResponseDTO.SUCCESS;
    }

    /**
     * 删除
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    @RequiresPermissions("knowledgePoint:delete")
    public BaseResponseDTO delete(String ids) throws Exception{
        knowledgePointService.deleteByIds(ids);
        return BaseResponseDTO.SUCCESS;
    }

    @RequestMapping("/downloadFile/{id}")
    public void downloadFile(@PathVariable("id") Integer id, final HttpServletResponse response) throws Exception{
        KnowledgePoint knowledgePoint = new KnowledgePoint();
        knowledgePoint.setId(id);
        knowledgePoint = knowledgePointService.selectOne(knowledgePoint);
        byte[] file = knowledgePoint.getFile();
        OutputStream outputSream = response.getOutputStream();
        outputSream.write(file);
        outputSream.flush();
    }

}
