package com.justgo.admin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.justgo.admin.dto.base.BaseResponseDTO;
import com.justgo.admin.dto.base.BootstrapTableResDTO;
import com.justgo.admin.entity.KnowledgeMore;
import com.justgo.admin.entity.SysUser;
import com.justgo.admin.service.KnowledgeMoreService;
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
 * 知识拓展
 *
 * @auth jp
 * @Date 2018/9/20
 */
@Controller
@RequestMapping("/knowledgeMore")
public class KnowledgeMoreController {
    private Logger logger = LogManager.getLogger(KnowledgeMoreController.class);

    @Autowired
    private KnowledgeMoreService knowledgeMoreService;

    @RequestMapping("/index")
    @RequiresPermissions("knowledgeMore:view")
    public String index() {
        return "knowledgeMore/index";
    }

    /**
     * 知识拓展列表
     *
     * @return
     */
    @RequestMapping("/list")
    @RequiresPermissions("knowledgeMore:list")
    @ResponseBody
    public BootstrapTableResDTO<KnowledgeMore> list(KnowledgeMore entity, Integer pageNumber, Integer pageSize) throws Exception{
        PageHelper.startPage(pageNumber == null ? 1 : pageNumber, pageSize == null ? 100 : pageSize);
        List<KnowledgeMore> knowledgeMores = knowledgeMoreService.selectKnowledgeMore(entity);
        PageInfo<KnowledgeMore> pages = new PageInfo<>(knowledgeMores);
        long total = pages.getTotal();
        BootstrapTableResDTO<KnowledgeMore> respDto = new BootstrapTableResDTO<>();
        respDto.setTotal(total);
        respDto.setRows(knowledgeMores);
        return respDto;
    }

    /**
     * 知识拓展列表
     *
     * @return
     */
    @RequestMapping("/detail")
    @ResponseBody
    @RequiresPermissions("knowledgeMore:detail")
    public KnowledgeMore detail(KnowledgeMore knowledgeMore) throws Exception{
        return (KnowledgeMore) knowledgeMoreService.selectOne(knowledgeMore);
    }

    /**
     * 创建知识拓展
     * @param knowledgeMore
     * @return
     */
    @RequestMapping("/create")
    @ResponseBody
    @RequiresPermissions("knowledgeMore:create")
    public BaseResponseDTO create(KnowledgeMore knowledgeMore, MultipartFile multipartFile) throws Exception{
        SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();

        knowledgeMore.setCreateTime(new Date());
        knowledgeMore.setCreateUser(sysUser.getFullName());
        if (multipartFile != null){
            byte[] file = multipartFile.getBytes();
            String originalFilename = multipartFile.getOriginalFilename();
            knowledgeMore.setFileName(originalFilename);
            knowledgeMore.setFile(file);
        }
        knowledgeMoreService.add(knowledgeMore);
        return BaseResponseDTO.SUCCESS;
    }

    /**
     * 更新知识拓展
     * @param knowledgeMore
     * @return
     */
    @RequestMapping("/modify")
    @ResponseBody
    @RequiresPermissions("knowledgeMore:modify")
    public BaseResponseDTO modify(KnowledgeMore knowledgeMore, MultipartFile multipartFile) throws Exception{
        if (multipartFile != null){
            byte[] file = multipartFile.getBytes();
            String originalFilename = multipartFile.getOriginalFilename();
            knowledgeMore.setFileName(originalFilename);
            knowledgeMore.setFile(file);
        }
        knowledgeMoreService.updateByPrimaryKeySelective(knowledgeMore);
        return BaseResponseDTO.SUCCESS;
    }

    /**
     * 删除
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    @RequiresPermissions("knowledgeMore:delete")
    public BaseResponseDTO delete(String ids) throws Exception{
        knowledgeMoreService.deleteByIds(ids);
        return BaseResponseDTO.SUCCESS;
    }

    @RequestMapping("/downloadFile/{id}")
    public void downloadFile(@PathVariable("id") Integer id, final HttpServletResponse response) throws Exception{
        KnowledgeMore knowledgeMore = new KnowledgeMore();
        knowledgeMore.setId(id);
        knowledgeMore = knowledgeMoreService.selectOne(knowledgeMore);
        byte[] file = knowledgeMore.getFile();
        OutputStream outputSream = response.getOutputStream();
        outputSream.write(file);
        outputSream.flush();
    }

}
