package com.justgo.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.justgo.admin.controller.utils.page.Page;
import com.justgo.admin.dto.ServiceSite.SysDictionaryDTO;
import com.justgo.admin.dto.ServiceSite.SysDictionaryDataDTO;
import com.justgo.admin.dto.base.BootstrapTableResDTO;
import com.justgo.admin.entity.SysDictionary;
import com.justgo.admin.entity.SysDictionaryData;
import com.justgo.admin.enums.RedisEnum;
import com.justgo.admin.service.SysDictionaryDataService;
import com.justgo.admin.service.SysDictionaryService;
import com.justgo.admin.utils.JsonUtils;
import com.justgo.admin.utils.excel.ExcelUtils;
import com.justgo.admin.utils.excel.JsGridReportBase;
import com.justgo.admin.utils.excel.TableData;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * 系统字典controller
 */
@Controller
@RequestMapping("/sysDict")
public class SysDictionaryController {

    private Logger logger = LogManager.getLogger(SysDictionaryController.class);
    @Autowired
    private SysDictionaryService sysDictionaryService;
    @Autowired
    private SysDictionaryDataService sysDictionaryDataService;
    @Autowired
    private RedisTemplate redisTemplate;

    /*@Value("#{configProperties['templet.excel.path']}")*/
    public String templetExcelPath;

    private static ObjectMapper objectMapper = new ObjectMapper();

    private String toJson(Object object) throws JsonProcessingException {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
        return objectMapper.writeValueAsString(object);
    }

    //字典列表跳转
    @RequestMapping({"/manageSysDict"})
    public String manageSysDict() {
        return "dict/manageSysDict";
    }

    //字典数据列表跳转
    @RequestMapping({"/manageSysDictData"})
    public String manage() {
        return "dict/manageSysDictData";
    }

    /**
     * 字典列表
     */
    @RequestMapping("/listSysDict")
    @ResponseBody
    public BootstrapTableResDTO<SysDictionary> listSysDict(SysDictionary entity, Integer pageNum, Integer pageSize) throws Exception {

        PageHelper.startPage(Optional.ofNullable(pageNum).orElse(0), pageSize);
        List<SysDictionary> sysDictionaries = sysDictionaryService.select(entity);
        PageInfo<SysDictionary> pageInfo = new PageInfo<>(sysDictionaries);
        BootstrapTableResDTO<SysDictionary> bootstrapTableResDTO = new BootstrapTableResDTO<>();
        bootstrapTableResDTO.setRows(sysDictionaries);
        bootstrapTableResDTO.setTotal(pageInfo.getTotal());
        return bootstrapTableResDTO;
    }

    /**
     * 字典数据列表
     */
    @RequestMapping("/listSysDictData")
    @ResponseBody
    public Page<SysDictionaryData> listSysDictData(SysDictionaryData entity, Integer pageNum, Integer pageSize) throws IOException {
        Page<SysDictionaryData> pageModel = new Page<>();

        pageModel.setRows(sysDictionaryDataService.doQuery(entity, pageNum, pageSize));
        pageModel.setPageNumber(pageNum == null ? new Integer(1) : pageNum);
        pageModel.setPageSize(pageSize == null ? new Integer(Integer.MAX_VALUE) : pageSize);
        pageModel.setTotal(sysDictionaryDataService.count(entity));
        return pageModel;
    }


    /**
     * 新增字典
     */
    @RequestMapping("/addSysDict")
    @ResponseBody
    public JSONObject addSysDict(SysDictionary entity, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=utf-8");
        JSONObject res = new JSONObject();

        //唯一限制查
        List<SysDictionary> sysDictionaryResult = sysDictionaryService.doQueryForOr(entity, 1, 1);
        if (sysDictionaryResult.size() > 0) {
            res.put("errorMsg", "新增失败，该字典已经存在！");
        } else {
            //新增
            entity.setCreated(new Date());
            sysDictionaryService.add(entity);
            res.put("count", "1");
        }
        return res;
    }


    /**
     * 新增字典数据
     */
    @RequestMapping("/addSysDictData")
    @RequiresPermissions("sysDict:add")
    @ResponseBody
    public JSONObject addSysDictData(SysDictionaryData entity, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=utf-8");
        JSONObject res = new JSONObject();

        //唯一限制查询
        List<SysDictionaryData> sysDictionaryDataResult = sysDictionaryDataService.doQueryForOr(entity, 1, 1);
        if (sysDictionaryDataResult.size() > 0) {
            res.put("errorMsg", "新增失败，该字典数据已经存在！");
        } else {
            //新增
            entity.setCreated(new Date());
            sysDictionaryDataService.add(entity);
            if (entity.getValid().equals("1")) {
                SysDictionaryData sysDictionaryData = new SysDictionaryData();
                BeanUtils.copyProperties(entity, sysDictionaryData);
                flushDictData(sysDictionaryData);
            }
            res.put("count", "1");
        }
        //response.getWriter().append(res.toJSONString());
        return res;
    }

    /**
     * 修改字典
     */
    @RequestMapping("/editSysDict")
    @ResponseBody
    public JSONObject editSysDict(SysDictionary entity, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=utf-8");
        JSONObject res = new JSONObject();
        int updateCount = 0;
        //唯一限制查(doQueryForOr同时也会通过id查询，所以肯定会有至少1条数据满足查询，所以大于1时则说明重复了)
        List<SysDictionary> sysDictionaryResult = sysDictionaryService.doQueryForOr(entity, 1, 1);
        if (sysDictionaryResult.size() > 1) {
            res.put("errorMsg", "修改失败，该字典已经存在！");
        } else {
            //db修改
            updateCount = sysDictionaryService.updateByPrimaryKeySelective(entity);
            //db修改成功并且dictValue有变的情况下才进行级联更新字典数据表
            Map<String, SysDictionaryData> sysDictDataMap = new HashMap<>();
            if (updateCount > 0 && !sysDictionaryResult.get(0).getDictValue().equals(entity.getDictValue())) {
                SysDictionaryDataDTO sysDictionaryDataParam = new SysDictionaryDataDTO();
                sysDictionaryDataParam.setDictValue(sysDictionaryResult.get(0).getDictValue());
                List<SysDictionaryData> sysDictionaryDataQuery = sysDictionaryDataService.accurateQuery(sysDictionaryDataParam);
                sysDictionaryDataQuery.stream().forEach(updateEntity -> {
                    updateEntity.setDictValue(entity.getDictValue());
                    updateEntity.setValid(entity.getValid());
                    //修改db
                    sysDictionaryDataService.updateByPrimaryKeySelective(updateEntity);
                    sysDictDataMap.put(updateEntity.getDictDataName(), updateEntity);
                });
            }
            //字典有效则重刷字典数据，无效则删除
            if (entity.getValid().equals("1")) {
                redisTemplate.opsForHash().put(RedisEnum.REDIS_SYS_DICT_KEY.getCode(), entity.getDictValue(), JsonUtils.toJson(sysDictDataMap));
            } else {
                redisTemplate.opsForHash().delete(RedisEnum.REDIS_SYS_DICT_KEY.getCode(), entity.getDictValue());
            }

        }
        res.put("count", updateCount);
        //response.getWriter().append(res.toJSONString());
        return res;
    }

    /**
     * 修改字典数据
     */
    @RequestMapping("/editSysDictData")
    @ResponseBody
    public JSONObject editSysDictData(SysDictionaryData entity, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=utf-8");
        JSONObject res = new JSONObject();

        int updateCount = 0;
        //唯一限制查询(doQueryForOr同时也会通过id查询，所以肯定会有至少1条数据满足查询，所以大于1时则说明重复了)
        List<SysDictionaryData> sysDictionaryDataResult = sysDictionaryDataService.doQueryForOr(entity, 1, 1);
        if (sysDictionaryDataResult.size() > 1) {
            res.put("errorMsg", "修改失败，该字典数据已经存在！");
        } else {
            //保存原值
            SysDictionaryData dto = new SysDictionaryData();
            dto.setId(entity.getId());
            SysDictionaryData oldDictData = sysDictionaryDataService.selectOne(dto);
            //修改
            updateCount = sysDictionaryDataService.updateByPrimaryKeySelective(entity);
            if (updateCount > 0) {
                //删除原本值
                String sysDictDataMapString = (String) redisTemplate.opsForHash().get(RedisEnum.REDIS_SYS_DICT_KEY.getCode(), oldDictData.getDictValue());
                if (!StringUtil.isEmpty(sysDictDataMapString)) {
                    JavaType javaType = JsonUtils.getCollectionType(HashMap.class, String.class, SysDictionaryData.class);
                    Map<String, SysDictionaryData> sysDictDataMap = JsonUtils.toObject(sysDictDataMapString, javaType);
                    sysDictDataMap.remove(oldDictData.getDictDataName());
                    redisTemplate.opsForHash().put(RedisEnum.REDIS_SYS_DICT_KEY.getCode(), oldDictData.getDictValue(), JsonUtils.toJson(sysDictDataMap));
                }
                //刷新值
                SysDictionaryData sysDictionaryData = new SysDictionaryData();
                BeanUtils.copyProperties(entity, sysDictionaryData);
                flushDictData(sysDictionaryData);
            }
        }
        res.put("count", updateCount);
        //response.getWriter().append(res.toJSONString());
        return res;
    }

    /**
     * 从db字典中重导数据到redis
     */
    @RequestMapping("/flushRedisData")
    @RequiresPermissions("sysDict:edit")
    @ResponseBody
    public JSONObject flushRedisData(HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=utf-8");
        JSONObject res = new JSONObject();
        try {
            //全局删除原本的redis
            redisTemplate.delete(RedisEnum.REDIS_SYS_DICT_KEY.getCode());

            int flushNum = 0;
            SysDictionaryDataDTO sysDictionaryDataDTO = new SysDictionaryDataDTO();
            SysDictionaryDTO sysDictionaryDTO = new SysDictionaryDTO();
            sysDictionaryDTO.setValid("1");
            List<SysDictionary> sysDictionaries = sysDictionaryService.doQuery(sysDictionaryDTO, 1, Integer.MAX_VALUE);
            List<SysDictionaryData> sysDictionaryDatas;
            Map<String, SysDictionaryData> sysDictDataMap;
            for (SysDictionary sysDictionary : sysDictionaries) {
                sysDictionaryDataDTO.setDictValue(sysDictionary.getDictValue());
                sysDictionaryDataDTO.setValid("1");
                sysDictionaryDatas = sysDictionaryDataService.accurateQuery(sysDictionaryDataDTO);
                flushNum += sysDictionaryDatas.size();
                sysDictDataMap = new HashMap<>();
                for (SysDictionaryData sysDictionaryData : sysDictionaryDatas) {
                    sysDictDataMap.put(sysDictionaryData.getDictDataName(), sysDictionaryData);
                }
                //导入新的redis
                redisTemplate.opsForHash().put(RedisEnum.REDIS_SYS_DICT_KEY.getCode(), sysDictionary.getDictValue(), JsonUtils.toJson(sysDictDataMap));
            }
            res.put("count", flushNum);
        } catch (Exception e) {
            logger.error("重导全局缓存失败", e);
            res.put("errorMsg", "重导全局缓存失败");
        }
        //response.getWriter().append(res.toJSONString());
        return res;
    }

    /**
     * 重导局部缓存
     */
    private void flushDictData(SysDictionaryData sysDictionaryData) {
        try {
            String sysDictDataMapString = (String) redisTemplate.opsForHash().get(RedisEnum.REDIS_SYS_DICT_KEY.getCode(), sysDictionaryData.getDictValue());
            if (!StringUtil.isEmpty(sysDictDataMapString)) {
                JavaType javaType = JsonUtils.getCollectionType(HashMap.class, String.class, SysDictionaryData.class);
                Map<String, SysDictionaryData> sysDictDataMap = JsonUtils.toObject(sysDictDataMapString, javaType);
                if (sysDictionaryData.getValid().equals("1")) {
                    sysDictDataMap.put(sysDictionaryData.getDictDataName(), sysDictionaryData);
                } else {
                    sysDictDataMap.remove(sysDictionaryData.getDictDataName());
                }
                redisTemplate.opsForHash().put(RedisEnum.REDIS_SYS_DICT_KEY.getCode(), sysDictionaryData.getDictValue(), JsonUtils.toJson(sysDictDataMap));
            } else {
                SysDictionaryDataDTO sysDictionaryDataDTO = new SysDictionaryDataDTO();
                sysDictionaryDataDTO.setDictValue(sysDictionaryData.getDictValue());
                sysDictionaryDataDTO.setValid("1");
                List<SysDictionaryData> sysDictionaryDatas = sysDictionaryDataService.accurateQuery(sysDictionaryDataDTO);
                if (null != sysDictionaryDatas && sysDictionaryDatas.size() > 0) {
                    Map<String, SysDictionaryData> sysDictDataMapNew = new HashMap<>();
                    sysDictionaryDatas.stream().forEach(entity -> {
                        sysDictDataMapNew.put(entity.getDictDataName(), entity);
                    });
                    redisTemplate.opsForHash().put(RedisEnum.REDIS_SYS_DICT_KEY.getCode(), sysDictionaryData.getDictValue(), JsonUtils.toJson(sysDictDataMapNew));
                }
            }
        } catch (Exception e) {
            logger.error("重导局部缓存失败", e);
        }
    }

    /**
     * 查询redis值，用于验证是否存到redis了
     */
    @RequestMapping("/findRedis")
    @ResponseBody
    public JSONObject findRedis(HttpServletResponse response, String dictValue, String dictDataName) throws IOException {
        String dictDataValue = sysDictionaryDataService.getSysDictDataValue(dictValue, dictDataName);
        response.setContentType("text/html; charset=utf-8");
        JSONObject res = new JSONObject();
        if (StringUtil.isEmpty(dictDataValue)) {
            dictDataValue = "空";
        }
        res.put("dictDataValue", dictDataValue);
        logger.info("=========findRedis========dictValue:" + dictValue + ",dictDataName:" + dictDataName + ",dictDataValue:" + dictDataValue);
        //response.getWriter().append(res.toJSONString());
        return res;
    }

    /**
     * 删除字典(暂时不做，此方法还没完善的)
     */
    @RequestMapping("/delSysDict")
    @ResponseBody
    public JSONObject delSysDict(SysDictionary entity, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=utf-8");
        JSONObject res = new JSONObject();

        int destroyCount = sysDictionaryService.deleteByPrimaryKey(entity.getId());
        res.put("count", destroyCount);

        //response.getWriter().append(res.toJSONString());
        return res;
    }

    /**
     * 删除字典数据
     */
    @RequestMapping("/delSysDictData")
    @ResponseBody
    public JSONObject delSysDictData(SysDictionaryData entity, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=utf-8");
        JSONObject res = new JSONObject();

        int destroyCount = sysDictionaryDataService.deleteByPrimaryKey(entity.getId());
        if (destroyCount == 1) {
            String sysDictDataMapString = (String) redisTemplate.opsForHash().get(RedisEnum.REDIS_SYS_DICT_KEY.getCode(), entity.getDictValue());
            if (!StringUtil.isEmpty(sysDictDataMapString)) {
                JavaType javaType = JsonUtils.getCollectionType(HashMap.class, String.class, SysDictionaryData.class);
                Map<String, SysDictionaryData> sysDictDataMap = JsonUtils.toObject(sysDictDataMapString, javaType);
                sysDictDataMap.remove(entity.getDictDataName());
                redisTemplate.opsForHash().put(RedisEnum.REDIS_SYS_DICT_KEY.getCode(), entity.getDictValue(), JsonUtils.toJson(sysDictDataMap));
            }
        }
        res.put("count", destroyCount);

        //response.getWriter().append(res.toJSONString());
        return res;
    }

    /**
     * 导出
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "report", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public void report(HttpServletRequest request, HttpServletResponse response) {
        //String path = templetExcelPath+"sysDictReport.xls";
        String path = "C:\\njcc\\sysDictReport.xls";
        SysDictionaryDataDTO sysDictionaryDataDTO = new SysDictionaryDataDTO();
        sysDictionaryDataDTO.setValid("1");
        sysDictionaryDataDTO.setOrders("sda.DICT_VALUE ASC");
        List<SysDictionaryData> dictionaryDatas = sysDictionaryDataService.doQuery(sysDictionaryDataDTO, 1, Integer.MAX_VALUE);
        String title = "系统字典数据汇总表";
        String[] hearders = {"字典名称", "字典值", "字典详细描述", "字典数据名", "字典数据值", "字典数据详细描述"};
        String[] fields = {"dictName", "dictValue", "dictDesc", "dictDataName", "dictDataValue", "dictDataDesc"};
        TableData td = ExcelUtils.createTableData(dictionaryDatas, ExcelUtils.createTableHeader(hearders), fields);
        try {
            JsGridReportBase report = new JsGridReportBase(request, response);
            report.exportToExcel(title, "admin", td, "系统字典数据汇总");
        } catch (Exception e) {
            logger.error("导出系统字典数据汇总表时出错" + e);
        }
    }

}
