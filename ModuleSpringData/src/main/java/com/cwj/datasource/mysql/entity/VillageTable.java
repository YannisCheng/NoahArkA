package com.cwj.datasource.mysql.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * (VillageTable)实体类
 *
 * @author makejava
 * @since 2021-08-02 21:08:15
 */
@ApiModel
public class VillageTable implements Serializable {
    private static final long serialVersionUID = 117672421711970244L;

    @ApiModelProperty(value = "数据插入id值")
    private Integer id;

    @ApiModelProperty(value = "村、社区名称")
    private String villageName;

    @ApiModelProperty(value = "村、社区编码")
    private String villageCode;

    @ApiModelProperty(value = "村、社区所在乡、镇级编码")
    private String townCode;

    @ApiModelProperty(value = "村、社区所在区、县级编码")
    private String countyCode;

    @ApiModelProperty(value = "村、社区所在市级编码")
    private String cityCode;

    @ApiModelProperty(value = "村、社区所在省级编码")
    private String provinceCode;

    @ApiModelProperty(value = "行政区划等级")
    private String grade;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public String getVillageCode() {
        return villageCode;
    }

    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
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