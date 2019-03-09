package com.justgo.admin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.justgo.admin.dto.base.BaseResponseDTO;
import com.justgo.admin.dto.base.BootstrapTableResDTO;
import com.justgo.admin.entity.QuestionAnswer;
import com.justgo.admin.entity.SysUser;
import com.justgo.admin.service.QuestionAnswerService;
import com.justgo.admin.service.QuestionAnswerService;
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
@RequestMapping("/questionAnswer")
public class QuestionAnswerController {
    private Logger logger = LogManager.getLogger(QuestionAnswerController.class);

    @Autowired
    private QuestionAnswerService questionAnswerService;

    @RequestMapping("/index")
    @RequiresPermissions("questionAnswer:view")
    public String index() {
        return "questionAnswer/index";
    }

    /**
     * 知识点列表
     *
     * @return
     */
    @RequestMapping("/list")
    @RequiresPermissions("questionAnswer:list")
    @ResponseBody
    public BootstrapTableResDTO<QuestionAnswer> list(QuestionAnswer entity, Integer pageNumber, Integer pageSize) throws Exception{
        PageHelper.startPage(pageNumber == null ? 1 : pageNumber, pageSize == null ? 100 : pageSize);
        List<QuestionAnswer> questionAnswers = questionAnswerService.selectQuestionAnswer(entity);
        PageInfo<QuestionAnswer> pages = new PageInfo<>(questionAnswers);
        long total = pages.getTotal();
        BootstrapTableResDTO<QuestionAnswer> respDto = new BootstrapTableResDTO<>();
        respDto.setTotal(total);
        respDto.setRows(questionAnswers);
        return respDto;
    }

    /**
     * 知识点列表
     *
     * @return
     */
    @RequestMapping("/detail")
    @ResponseBody
    @RequiresPermissions("questionAnswer:detail")
    public QuestionAnswer detail(QuestionAnswer questionAnswer) throws Exception{
        return (QuestionAnswer) questionAnswerService.selectOne(questionAnswer);
    }

    /**
     * 创建知识点
     * @param questionAnswer
     * @return
     */
    @RequestMapping("/create")
    @ResponseBody
    @RequiresPermissions("questionAnswer:create")
    public BaseResponseDTO create(QuestionAnswer questionAnswer, MultipartFile multipartFile) throws Exception{
        SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();

        questionAnswer.setQuestionTime(new Date());
        questionAnswer.setQuestioner(sysUser.getFullName());
        if (multipartFile != null){
            byte[] file = multipartFile.getBytes();
            String originalFilename = multipartFile.getOriginalFilename();
            questionAnswer.setFileName(originalFilename);
            questionAnswer.setFile(file);
        }
        questionAnswerService.add(questionAnswer);
        return BaseResponseDTO.SUCCESS;
    }

    /**
     * 更新知识点
     * @param questionAnswer
     * @return
     */
    @RequestMapping("/modify")
    @ResponseBody
    @RequiresPermissions("questionAnswer:modify")
    public BaseResponseDTO modify(QuestionAnswer questionAnswer, MultipartFile multipartFile) throws Exception{
        SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();

        questionAnswer.setAnswerTime(new Date());
        questionAnswer.setAnswerer(sysUser.getFullName());
        if (multipartFile != null){
            byte[] file = multipartFile.getBytes();
            String originalFilename = multipartFile.getOriginalFilename();
            questionAnswer.setFileName(originalFilename);
            questionAnswer.setFile(file);
        }
        questionAnswerService.updateByPrimaryKeySelective(questionAnswer);
        return BaseResponseDTO.SUCCESS;
    }

    /**
     * 删除
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    @RequiresPermissions("questionAnswer:delete")
    public BaseResponseDTO delete(String ids) throws Exception{
        questionAnswerService.deleteByIds(ids);
        return BaseResponseDTO.SUCCESS;
    }

    @RequestMapping("/downloadFile/{id}")
    public void downloadFile(@PathVariable("id") Integer id, final HttpServletResponse response) throws Exception{
        QuestionAnswer questionAnswer = new QuestionAnswer();
        questionAnswer.setId(id);
        questionAnswer = questionAnswerService.selectOne(questionAnswer);
        byte[] file = questionAnswer.getFile();
        OutputStream outputSream = response.getOutputStream();
        outputSream.write(file);
        outputSream.flush();
    }

}
