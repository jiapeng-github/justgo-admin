package com.justgo.admin.controller;

import com.alibaba.fastjson.JSON;
import com.justgo.admin.dto.base.BaseResponseDTO;
import com.justgo.admin.dto.login.LoginRequestDTO;
import com.justgo.admin.dto.menu.MenuDTO;
import com.justgo.admin.dto.menu.MenuItemDTO;
import com.justgo.admin.entity.SysResource;
import com.justgo.admin.enums.AdminResponseEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户
 */
@RequestMapping("user")
@Controller
public class UserController {

    private Logger logger = LogManager.getLogger(UserController.class);

    /**
     * 登录页面
     *
     * @return
     */
    @RequestMapping("/loginPage")
    public String loginPage(HttpServletRequest request, HttpServletResponse response) {
        String asynch = request.getHeader("X-Requested-With");
        if (asynch !=null) {
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            try (PrintWriter writer = response.getWriter()) {
                writer.write(JSON.toJSONString(new BaseResponseDTO(AdminResponseEnum.NO_LOGIN)));
                return null;
            }catch (Exception ignored){

            }
        }
        return "login";
    }

    /**
     * 登录
     *
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public BaseResponseDTO login(LoginRequestDTO loginRequestDTO) {
        UsernamePasswordToken token = new UsernamePasswordToken(loginRequestDTO.getUserName(),
                                                                loginRequestDTO.getPassword());
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            logger.info("登录失败!!!{}", JSON.toJSONString(loginRequestDTO));
           return new BaseResponseDTO(AdminResponseEnum.FAIL);
        }
        return BaseResponseDTO.SUCCESS;
    }

    /**
     * 获取菜单
     *
     * @return
     */
    @RequestMapping("/getMenu")
    @ResponseBody
    @RequiresPermissions("menu:view")
    public List<MenuDTO> getMenu() {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        List<SysResource> sysResources = (List<SysResource>) session.getAttribute("_sysResources");

        List<MenuDTO> result = new ArrayList<>();
        if (sysResources == null || sysResources.isEmpty()) return result;

        // 一级菜单
        List<SysResource> one = sysResources.stream().filter(sysResource -> sysResource.getFId() == null).collect(Collectors.toList());
        // 设置一级菜单
        one.forEach(sysResource -> {
            MenuDTO menuDTO = new MenuDTO();
            menuDTO.setId(sysResource.getId());
            menuDTO.setIcon(sysResource.getIcon());
            menuDTO.setMenu(sysResource.getResourceName());
            menuDTO.setList(new ArrayList<>());
            result.add(menuDTO);
        });

        if (result.isEmpty()) return result;

        //设置二级菜单
        result.forEach(
                menuDTO ->
                        sysResources.stream().filter(sysResource -> menuDTO.getId().equals(sysResource.getFId()) && sysResource.getResourceType().equals(2))
                                    .forEach(sysResource -> {
                                        MenuItemDTO menuItemDTO = new MenuItemDTO();
                                        menuItemDTO.setName(sysResource.getResourceName());
                                        menuItemDTO.setUrl(sysResource.getResourceUrl());
                                        menuDTO.getList().add(menuItemDTO);
                                    }));

        return result;
    }


}
