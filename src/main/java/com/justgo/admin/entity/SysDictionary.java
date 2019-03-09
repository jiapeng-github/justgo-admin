package com.justgo.admin.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 系统字典表对应实体
 * Created by fancz on 2017/4/27.
 */
public class SysDictionary{

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
     * 字典名字
     */
    @Column(name = "DICT_NAME")
    private String dictName;
    /**
     * 字典值
     */
    @Column(name = "DICT_VALUE")
    private String dictValue;
    /**
     * 详细描述
     */
    @Column(name = "DICT_DESC")
    private String dictDesc;
    /**
     * 是否有效 0为无效  1为有效
     */
    @Column(name = "VALID")
    private String valid;

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

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
    }

    public String getDictDesc() {
        return dictDesc;
    }

    public void setDictDesc(String dictDesc) {
        this.dictDesc = dictDesc;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    @Override
    public String toString() {
        return "SysDictionary{" +
                "dictName='" + dictName + '\'' +
                ", dictValue='" + dictValue + '\'' +
                ", dictDesc='" + dictDesc + '\'' +
                ", valid='" + valid + '\'' +
                '}';
    }
}
