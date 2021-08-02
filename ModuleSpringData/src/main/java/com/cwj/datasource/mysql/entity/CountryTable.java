package com.cwj.datasource.mysql.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * com.cwj.datasource.mysql.entity
 *
 * @author ChengWenjia  cwj1714@163.com
 * @date 2021-08-02 16:19
 */
@Data
@ApiModel
public class CountryTable implements Serializable {

    @ApiModelProperty(value = "区县乡镇id值")
    private Integer id;

    @ApiModelProperty(value = "区县乡镇名称")
    private String countryName;

    @ApiModelProperty(value = "区县乡镇编码")
    private String countryCode;

    @ApiModelProperty(value = "区县乡镇所在城市的id值")
    private String cityCode;

    @ApiModelProperty(value = "区县乡镇所在省的id值")
    private String provinceCode;

    @ApiModelProperty(value = "行政区划所在等级")
    private String grade;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
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

    @Override
    public String toString() {
        return "CountryTable{" +
                "id=" + id +
                ", countryName='" + countryName + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", cityCode='" + cityCode + '\'' +
                ", provinceCode='" + provinceCode + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }
}

