package com.cwj.datasource.mysql.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * (CityTable)实体类
 *
 * @author makejava
 * @since 2021-08-02 11:04:41
 */
@ApiModel
@Data
public class CityTable implements Serializable {
    private static final long serialVersionUID = 290522171112443512L;

    @ApiModelProperty(value = "数据插入id值")
    private Integer id;

    @ApiModelProperty(value = "城市名称")
    private String cityName;

    @Getter
    @Setter
    @ApiModelProperty(value = "城市编码")
    private String cityCode;

    @ApiModelProperty(value = "城市所在省级编码")
    private String provinceCode;

    @ApiModelProperty(value = "行政区划等级")
    private String grade;

}