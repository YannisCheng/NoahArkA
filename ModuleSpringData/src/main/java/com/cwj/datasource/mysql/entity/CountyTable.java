package com.cwj.datasource.mysql.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * (CountyTable)实体类
 *
 * @author makejava
 * @since 2021-08-02 20:38:19
 */
@ApiModel
public class CountyTable implements Serializable {
    private static final long serialVersionUID = 585368011007188651L;

    @ApiModelProperty(value = "数据插入id值")
    private Integer id;

    @ApiModelProperty(value = "区、县名称")
    private String countyName;

    @ApiModelProperty(value = "区、县编码")
    private String countyCode;

    @ApiModelProperty(value = "区、县所在市级编码")
    private String cityCode;

    @ApiModelProperty(value = "区、县所在城省级编码")
    private String provinceCode;

    @ApiModelProperty(value = "行政区划等级")
    private String grade;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
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