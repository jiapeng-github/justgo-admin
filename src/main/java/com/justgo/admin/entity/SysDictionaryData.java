package com.justgo.admin.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 系统字典数据表对应实体
 * Created by fancz on 2017/4/27.
 */
@Table(name = "SYS_DICTIONARYDATA")
public class SysDictionaryData {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 创建时间
     */
    @Column(name = "CREATED")
    private Date created;

    /**
     * dictionary中的值
     */
    @Column(name = "DICT_VALUE")
    private String dictValue;
    /**
     * 字典数据名字
     */
    @Column(name = "DICTDATA_NAME")
    private String dictDataName;
    /**
     * 字典数据值
     */
    @Column(name = "DICTDATA_VALUE")
    private String dictDataValue;
    /**
     * 详细描述
     */
    @Column(name = "DICTDATA_DESC")
    private String dictDataDesc;
    /**
     * 是否有效 0为无效  1为有效
     */
    @Column(name = "VALID")
    private String valid;

    @Transient
    private String dictName;//字典名
    @Transient
    private String dictDesc;//字典描述

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
    }

    public String getDictDataName() {
        return dictDataName;
    }

    public void setDictDataName(String dictDataName) {
        this.dictDataName = dictDataName;
    }

    public String getDictDataValue() {
        return dictDataValue;
    }

    public void setDictDataValue(String dictDataValue) {
        this.dictDataValue = dictDataValue;
    }

    public String getDictDataDesc() {
        return dictDataDesc;
    }

    public void setDictDataDesc(String dictDataDesc) {
        this.dictDataDesc = dictDataDesc;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    @Override
    public String toString() {
        return "SysDictionaryData{" +
                "dictValue='" + dictValue + '\'' +
                ", dictDataName='" + dictDataName + '\'' +
                ", dictDataValue='" + dictDataValue + '\'' +
                ", dictDataDesc='" + dictDataDesc + '\'' +
                ", valid='" + valid + '\'' +
                '}';
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public String getDictDesc() {
        return dictDesc;
    }

    public void setDictDesc(String dictDesc) {
        this.dictDesc = dictDesc;
    }
}
