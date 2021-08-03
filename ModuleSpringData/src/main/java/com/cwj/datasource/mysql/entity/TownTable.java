package com.cwj.datasource.mysql.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * (TownTable)实体类
 *
 * @author makejava
 * @since 2021-08-02 20:55:17
 */
@ApiModel
public class TownTable implements Serializable {
    private static final long serialVersionUID = -60955001638906866L;

    @ApiModelProperty(value = "数据插入id值")
    private Integer id;

    @ApiModelProperty(value = "乡、镇名称")
    private String townName;

    @ApiModelProperty(value = "乡、镇编码")
    private String townCode;

    @ApiModelProperty(value = "乡、镇所在区、县编码")
    private String countyCode;

    @ApiModelProperty(value = "乡、镇所在市级编码")
    private String cityCode;

    @ApiModelProperty(value = "乡、镇所在省级编码")
    private String provinceCode;

    @ApiModelProperty(value = "行政区划等级")
    private String grade;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public String getTownCode() {
        return townCode;
    }

    public void setTownCode(String townCode) {
        this.townCode = townCode;
    }

    public String getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

}