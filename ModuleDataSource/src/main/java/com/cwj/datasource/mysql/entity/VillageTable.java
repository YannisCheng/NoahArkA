package com.cwj.datasource.mysql.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * (VillageTable)实体类
 *
 * @author makejava
 * @since 2021-08-02 21:08:15
 */
@Data
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

}