package com.justgo.admin.controller.base;

import com.github.pagehelper.PageInfo;
import com.justgo.admin.controller.utils.ResetUtil;
import com.justgo.admin.controller.utils.page.Page;
import com.justgo.admin.dto.base.BaseResponseDTO;
import com.justgo.admin.service.BaseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.lang.reflect.Field;

import static com.justgo.admin.enums.AdminResponseEnum.FAIL;
import static com.justgo.admin.enums.AdminResponseEnum.SUCCESS;

@Lazy
public abstract class AbstractBaseController<E,S extends BaseService<E,?,?>> {

    private Logger logger = LogManager.getLogger(AbstractBaseController.class);
    @Autowired
    protected S baseService;

    @RequestMapping("/list")
    @ResponseBody
    @RequiresPermissions(":view")
    public Page<E> list(E entity, Integer pageNumber, Integer pageSize) throws Exception{
        ResetUtil.resetObject(entity);//重置属性，把""改为null
        Page<E> pageModel = new Page<>();
        PageInfo<E> pageInfo = baseService.selectPage(entity, pageNumber, pageSize);
        pageModel.setRows(pageInfo.getList());
        pageModel.setTotal(pageInfo.getTotal());
        pageModel.setPageNumber(pageNumber);
        pageModel.setPageSize(pageSize);
        return pageModel;
    }

    @RequestMapping("/add")
    @ResponseBody
    public BaseResponseDTO create(E entity) {
        int i = baseService.add(entity);
        if (i > 0) return new BaseResponseDTO(SUCCESS);
        return new BaseResponseDTO(FAIL);
    }

    @RequestMapping("/edit")
    @ResponseBody
    public BaseResponseDTO update(E entity) throws IOException {
        if (!checkId(entity)) return new BaseResponseDTO(FAIL);
        int i = baseService.updateByPrimaryKeySelective(entity);
        if (i > 0) return new BaseResponseDTO(SUCCESS);
        return new BaseResponseDTO(FAIL);
    }

    @RequestMapping("/del")
    @ResponseBody
    public BaseResponseDTO destroy(E entity) throws IOException {
        if (!checkId(entity)) return new BaseResponseDTO(FAIL);
        int i = baseService.delete(entity);
        if (i > 0) return new BaseResponseDTO(SUCCESS);
        return new BaseResponseDTO(FAIL);
    }

    //校验id属性不为空
    protected boolean checkId(E bo) {
        if (bo == null) {
            return false;
        }
        Class clz = bo.getClass();
        Field[] f = clz.getDeclaredFields();
        try {
            for (Field aF : f) {
                aF.setAccessible(true);
                //id字段不能为空
                if (aF.getName().equalsIgnoreCase("id") && (aF.get(bo) == "" || aF.get(bo) == null)) {
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
