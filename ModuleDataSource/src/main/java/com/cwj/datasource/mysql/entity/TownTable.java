package com.cwj.datasource.mysql.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * (TownTable)实体类
 *
 * @author makejava
 * @since 2021-08-02 20:55:17
 */
@Data
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

}