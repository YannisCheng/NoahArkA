package com.cwj.datasource.mysql.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * (ProvinceTable)实体类
 *
 * @author makejava
 * @since 2021-08-02 20:53:07
 */
@Data
@ApiModel
public class ProvinceTable implements Serializable {
    private static final long serialVersionUID = 719595846326765965L;

    @ApiModelProperty(value = "数据插入id值")
    private Integer id;

    @ApiModelProperty(value = "省名称")
    private String provinceName;

    @ApiModelProperty(value = "省编码")
    private String provinceCode;

    @ApiModelProperty(value = "行政区划等级")
    private String grade;

}